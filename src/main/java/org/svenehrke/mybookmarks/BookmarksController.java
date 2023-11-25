package org.svenehrke.mybookmarks;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestClient;

import java.math.BigInteger;
import java.net.URI;
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
		RestClient restClient = RestClient.create();
		URI uri = URI.create(bookmark.url());
		var domain = "%s://%s".formatted(uri.getScheme(), uri.getHost());
		String url = "%s/%s".formatted(domain, uri.getPath());
		String html = restClient.get().uri(url).retrieve().body(String.class);

		Document doc = Jsoup.parse(html);

		String ogImageContent = getOpenGraphElementsContent(doc, "og:image", "https://placehold.co/250x100/png");
		var card = new Card(
			url,
			uri.getHost(),
			ogImageContent.startsWith("http") ? ogImageContent : "%s/%s".formatted(domain, ogImageContent),
			getOpenGraphElementsContent(doc, "og:title", ""),
			getOpenGraphElementsContent(doc, "og:description", "")
		);
		model.addAttribute("card", card);
		return "bookmarks/card";
	}

	private static String getOpenGraphElementsContent(Document doc, String ogName, String orElse) {
		Element select = doc.select("head meta[property='%s']".formatted(ogName)).first();
		return (select != null) ? select.attributes().get("content") : orElse;
	}


}
