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
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigInteger;

@Controller
@AllArgsConstructor
@Slf4j
public class BookmarksController {

	private final BookmarkService bookmarkService;
	private final BookmarkSessionStore bookmarkSessionStore;

	@GetMapping("/")
	public RedirectView index() {
		return new RedirectView("/bookmarkspage");
	}

	@GetMapping("/bookmarks")
	public String bookmarks(
		@RequestParam(required = false, name = "search_by_tags") String searchByTags,
		@RequestParam(required = false, name = "search_by_title") String searchByTitle,
		Model model
	) {
		model.addAttribute("bookmarks", bookmarkService.findByTag(searchByTags));
		return "bookmarks/fragment/bookmark_rows";
	}

	@GetMapping("/page/{id}")
	public String page(HttpServletRequest request, @PathVariable BigInteger id, Model model) {
		var bookmarks = bookmarkSessionStore.getBookmarks();
		Bookmark bookmark = bookmarkService.getById(id, bookmarks);

		BookmarkRetriever bookmarkRetriever = new BookmarkRetriever(bookmark);
		model.addAttribute("card", bookmarkRetriever.getCard());
		return "bookmarks/fragment/card";
	}

	@PostMapping("/reload")
	@ResponseBody
	public String reload(HttpServletResponse response, Model model) {
		bookmarkService.reload();
		response.setHeader("HX-Trigger", "bookmarkAdded");
		return "";
	}

	@PostMapping(path = "/bookmark", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	@ResponseBody
	public String addBookmark(@RequestParam(required = true, name = "bm-url") String bmUrl, HttpServletResponse response) {
		bookmarkService.loadBookmarksIntoSessionIfNecessary();
		var newLine = bmUrl + ";todo" + System.lineSeparator();
		var csv = newLine + bookmarkSessionStore.getBookmarksCSV();
		bookmarkService.reCreateBookmarks(csv);

		response.setHeader("HX-Trigger", "bookmarkAdded");
		response.setStatus(HttpStatus.CREATED.value());
		return "";
	}

}
