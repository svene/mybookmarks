package org.svenehrke.mybookmarks;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClient;

import java.math.BigInteger;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class BookmarksController {

	public static final String KEY_BOOKMARKS_CSV = "bookmarks-csv";
	public static final String KEY_BOOKMARKS = "bookmarks";
	private final BookmarkService bookmarkService;

	@GetMapping("/main")
	public String main(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(true);
		session.setAttribute(KEY_BOOKMARKS_CSV, bookmarkService.readCsvAsString());
		return "bookmarks/main";
	}
	@GetMapping("/bookmarks")
	public String bookmarks(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		String csv = (String) session.getAttribute(KEY_BOOKMARKS_CSV);
		List<Bookmark> bookmarks = bookmarkService.readCsv(csv);
		session.setAttribute(KEY_BOOKMARKS, bookmarks);
		model.addAttribute("urls", bookmarks);

		return "bookmarks/bookmarks";
	}

	@GetMapping("/page/{id}")
	public String page(HttpServletRequest request, @PathVariable BigInteger id, Model model) {
		HttpSession session = request.getSession(false);
		var bookmarks = (List<Bookmark>) session.getAttribute(KEY_BOOKMARKS);
		Bookmark bookmark = bookmarkService.getById(id, bookmarks);

		URI uri = URI.create(bookmark.url());
		String html = makeHttpCall(uri, true);
		Document doc = Jsoup.parse(html);

		String ogImageContent = getOpenGraphElementsContent(doc, "og:image", "https://placehold.co/250x100/png?text=:-)");
		var card = new Card(
			bookmark.url(),
			uri.getHost(),
			newUrlFromPossiblyRelativeUrl(uri, ogImageContent),
			getOpenGraphElementsContent(doc, "og:title", ""),
			getOpenGraphElementsContent(doc, "og:description", "")
		);
		model.addAttribute("card", card);
		return "bookmarks/card";
	}

	@PostMapping("/bookmarks")
	public String reload(HttpServletRequest request, Model model) {
		bookmarkService.reload();
		return "bookmarks/bookmarks";
	}

	@GetMapping("/csv")
	@ResponseBody
	public String csv(HttpServletRequest request, Model model) {
		String csv = String.join(System.lineSeparator(), Arrays.asList(
			"9;https://programmingpercy.tech/blog/opengraph-protocol-how-and-why",
			"9;https://youtu.be/c7pgqHNTXQM?si=GnOyPeJzK_tBgp4c",
			"9;https://www.youtube.com/watch?v=qsk2JZT_vIc",
			"9;https://medium.com/@nuno.mt.sousa/part-ii-creating-a-kafka-cluster-test-extension-569ed750d137",
			"9;https://www.heise.de"
			));
		return csv;
	}


	private String makeHttpCall(URI uri, boolean followRedirect) {
		List<HttpStatusCode> redirectHttpStatusCodes = Arrays.asList(301, 303).stream().map(it -> HttpStatusCode.valueOf(it)).toList();
		RestClient restClient = RestClient.create();
		log.debug("uri = {0}", uri);
		ResponseEntity<String> result = restClient
			.get()
			.uri(uri)
			.retrieve()
			.toEntity(String.class);
		log.debug("status = {0}", result.getStatusCode());

		if (redirectHttpStatusCodes.contains(result.getStatusCode())) {
			if (followRedirect) {
				var hs = result.getHeaders();
				String location = hs.get("location").get(0);
				location = newUrlFromPossiblyRelativeUrl(uri, location);
				log.debug("location = {0}", location);
				return makeHttpCall(URI.create(location), false);
			} else {
				return "";
			}
		} else {
			return result.getBody();
		}
	}

	private static String newUrlFromPossiblyRelativeUrl(URI baseUri, String url) {
		if (url.startsWith("http")) {
			return url;
		}
		String format = url.startsWith("/") ? "%s%s" : "%s/%s";
		return format.formatted(getDomain(baseUri), url);
	}
	private static String getDomain(URI uri) {
		return "%s://%s".formatted(uri.getScheme(), uri.getHost());
	}
	private static String getOpenGraphElementsContent(Document doc, String ogName, String orElse) {
		Element select = doc.select("head meta[property='%s']".formatted(ogName)).first();
		return (select != null) ? select.attributes().get("content") : orElse;
	}


}
