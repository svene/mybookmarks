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
import org.springframework.web.client.RestClient;

import java.math.BigInteger;
import java.net.URI;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class BookmarksController {

	@GetMapping("/bookmarks")
	public String bookmarks(HttpServletRequest request, Model model) {
		var urls = List.of(
			new Bookmark(BigInteger.valueOf(1), "https://programmingpercy.tech" + "/blog/opengraph-protocol-how-and-why"),
			new Bookmark(BigInteger.valueOf(2), "https://programmingpercy.tech" + "/blog/opengraph-protocol-how-and-why")
		);
		model.addAttribute("urls", urls);

		return "bookmarks/bookmarks";
	}

	@GetMapping("/page")
//	@ResponseBody
	public String page(HttpServletRequest request, Model model) {

		RestClient restClient = RestClient.create();
		URI uri = URI.create("https://programmingpercy.tech/blog/opengraph-protocol-how-and-why");
		var domain = "%s://%s".formatted(uri.getScheme(), uri.getHost());
		String url = "%s/%s".formatted(domain, uri.getPath());
		String html = restClient.get().uri(url).retrieve().body(String.class);

		Document doc = Jsoup.parse(html);

		var card = new Card(
			url,
			uri.getHost(),
			"%s/%s".formatted(domain, getOpenGraphElementsContent(doc, "og:image")),
			getOpenGraphElementsContent(doc, "og:title"),
			getOpenGraphElementsContent(doc, "og:description")
		);
		model.addAttribute("card", card);
		return "bookmarks/card";
	}

	private static String getOpenGraphElementsContent(Document doc, String ogName) {
		Element select = doc.select("head meta[property='%s']".formatted(ogName)).first();
		return select.attributes().get("content");
	}


}
