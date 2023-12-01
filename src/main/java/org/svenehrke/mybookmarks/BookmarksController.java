package org.svenehrke.mybookmarks;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@Controller
@AllArgsConstructor
@Slf4j
public class BookmarksController {

	private final BookmarkService bookmarkService;
	private final BookmarkSessionStore bookmarkSessionStore;

	@GetMapping("/main")
	public String main(HttpServletRequest request, Model model) {
		bookmarkService.loadBookmarksIntoSessionIfNecessary();
		return "bookmarks/main";
	}
	@GetMapping("/bookmarks")
	public String bookmarks(HttpServletRequest request, Model model) {
		bookmarkService.loadBookmarksIntoSessionIfNecessary();
		model.addAttribute("urls", bookmarkSessionStore.getBookmarks());

		return "bookmarks/bookmarkspage";
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
		return "bookmarks/bookmarkspage";
	}

	@PostMapping(path = "/bookmark", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	@ResponseBody
	public String addBookmark(@RequestParam(required = true, name = "bm-url") String bmUrl, HttpServletResponse response) {
		bookmarkService.loadBookmarksIntoSessionIfNecessary();
		var newLine = bmUrl + ";todo" + System.lineSeparator();
		var csv = newLine + bookmarkSessionStore.getBookmarksCSV();
		bookmarkSessionStore.setBookmarksCSV(csv);
		bookmarkService.reCreateBookmarks();

		response.setHeader("HX-Trigger", "bookmarkAdded");
		response.setStatus(HttpStatus.CREATED.value());
		return "";
	}

	@GetMapping("/csv")
	@ResponseBody
	public String csv(HttpServletRequest request, Model model) {
		String csv = bookmarkSessionStore.getBookmarksCSV();
		return csv;
	}

	@GetMapping("/page/csv")
	public String csvPage(HttpServletRequest request, Model model) {
		bookmarkService.loadBookmarksIntoSessionIfNecessary();
		model.addAttribute("csvString", bookmarkSessionStore.getBookmarksCSV());
		return "bookmarks/csvpage";
	}

}
