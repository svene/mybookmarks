package org.svenehrke.mybookmarks;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class BookmarksController {

	private final BookmarkService bookmarkService;
	private final BookmarkSessionStore bookmarkSessionStore;

	@GetMapping("/main")
	public String main(HttpServletRequest request, Model model) {
		bookmarkSessionStore.setBookmarksCSV(bookmarkService.readCsvAsString());
		return "bookmarks/main";
	}
	@GetMapping("/bookmarks")
	public String bookmarks(HttpServletRequest request, Model model) {
		String csv = bookmarkSessionStore.getBookmarksCSV();
		List<Bookmark> bookmarks = bookmarkService.readCsv(csv);
		bookmarkSessionStore.setBookmarks(bookmarks);
		model.addAttribute("urls", bookmarks);

		return "bookmarks/bookmarks";
	}

	@GetMapping("/page/{id}")
	public String page(HttpServletRequest request, @PathVariable BigInteger id, Model model) {
		var bookmarks = bookmarkSessionStore.getBookmarks();
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
		return bookmarkService.getCsvAsString();
	}

	@GetMapping("/page/csv")
	public String csvPage(HttpServletRequest request, Model model) {
		model.addAttribute("csvString", bookmarkService.getCsvAsString());
		return "bookmarks/csvpage";
	}

}
