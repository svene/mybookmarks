package org.svenehrke.mybookmarks;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
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

		BookmarkRetriever bookmarkRetriever = new BookmarkRetriever(bookmark);
		model.addAttribute("card", bookmarkRetriever.getCard());
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
			"https://www.freecodecamp.org/news/what-is-tailwind-css-a-beginners-guide/;read",
			"https://elanna.me/blog/2023/11/time-for-a-change-switching-to-analog;read",
			"https://www.youtube.com/watch?v=cgnrB5PkaBo;read",
			"https://htmx.org/essays/why-tend-not-to-use-content-negotiation/;read",
			"https://docs.localstack.cloud/academy/;read",
			"https://www.youtube.com/watch?v=h8Jth_ijZyY&t=1s;read",
			"https://thenewstack.io/how-to-use-databases-inside-github-actions/;read",
			"https://odrotbohm.de/2023/07/sliced-onion-architecture/;read",

			"https://programmingpercy.tech/blog/opengraph-protocol-how-and-why;sweng",
			"https://youtu.be/c7pgqHNTXQM?si=GnOyPeJzK_tBgp4c;sweng",
			"https://www.youtube.com/watch?v=qsk2JZT_vIc;sweng",
			"https://medium.com/@nuno.mt.sousa/part-ii-creating-a-kafka-cluster-test-extension-569ed750d137;sweng",
			"https://www.heise.de;news"
			));
		return csv;
	}





}
