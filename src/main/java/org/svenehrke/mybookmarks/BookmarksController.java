package org.svenehrke.mybookmarks;

import jakarta.servlet.http.HttpServletRequest;
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
import org.springframework.web.client.RestClient;

import java.math.BigInteger;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class BookmarksController {

	private final BookmarkService bookmarkService;

	@GetMapping("/bookmarks")
	public String bookmarks(HttpServletRequest request, Model model) {
		var urls = List.of(
			new Bookmark(BigInteger.valueOf(1), "https://programmingpercy.tech" + "/blog/opengraph-protocol-how-and-why"),
			new Bookmark(BigInteger.valueOf(2), "https://programmingpercy.tech" + "/blog/opengraph-protocol-how-and-why")
		);
		model.addAttribute("urls", bookmarkService.getAllBookmarks());

		return "bookmarks/bookmarks";
	}

	@GetMapping("/page/{id}")
	public String page(HttpServletRequest request, @PathVariable BigInteger id, Model model) {
		Bookmark bookmark = bookmarkService.getById(id);

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

	private String makeHttpCall(URI uri, boolean followRedirect) {
		List<HttpStatusCode> redirectHttpStatusCodes = Arrays.asList(301, 303).stream().map(it -> HttpStatusCode.valueOf(it)).toList();
		RestClient restClient = RestClient.create();
		System.out.println("uri = " + uri);
		String url = "%s/%s".formatted(getDomain(uri), uri.getPath());
		ResponseEntity<String> result = restClient
			.get()
			.uri(uri)
			.retrieve()
			.toEntity(String.class);
		System.out.println("status = " + result.getStatusCode());

		String html = "";
		if (redirectHttpStatusCodes.contains(result.getStatusCode())) {
			if (followRedirect) {
				var hs = result.getHeaders();
				String location = hs.get("location").get(0);
				location = newUrlFromPossiblyRelativeUrl(uri, location);
				System.out.println(location);
				html = makeHttpCall(URI.create(location), false);
			} else {
				html = "";
			}
		} else {
			html = result.getBody();
		}
		return html;
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
