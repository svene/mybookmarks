package org.svenehrke.mybookmarks;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClient;

import java.math.BigInteger;
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
		var domain = "https://programmingpercy.tech";
		String url = domain + "/blog/opengraph-protocol-how-and-why";
		String html = restClient.get().uri(url).retrieve().body(String.class);

		Document doc = Jsoup.parse(html);
		Element select = doc.select("head meta[property='og:image']").first();
		var ogImage = select.attributes().get("content");
//		ogImage = "https://programmingpercy.tech/_app/immutable/assets/img0-346f98ae.webp";

		String s = "<img src='%s' alt='Open Graph Image'>".formatted(domain + "/" + ogImage);
		var card = new Card(url, domain + "/" + ogImage);
		model.addAttribute("card", card);
		return "bookmarks/card";
	}



}
