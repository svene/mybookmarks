package org.svenehrke.mybookmarks;

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
		return new RedirectView("/pagex/bookmarks");
	}

	@PutMapping("/search/tags")
	@ResponseBody
	public String searchTags(
		@RequestParam(required = false, name = "search_by_tags") String searchByTags,
		HttpServletResponse response
	) {
		bookmarkSessionStore.setSearchTags(searchByTags);
		response.setHeader("HX-Trigger", "searchTagsChanged");
		return "";
	}

	@GetMapping("/page/{id}")
	public String page(@PathVariable BigInteger id, Model model) {
		var bookmarks = bookmarkSessionStore.getBookmarks();
		Bookmark bookmark = bookmarkService.getById(id, bookmarks);

		bookmarkService.createBookmarkExIfNecessary(bookmark);
		model.addAttribute("card", new BookmarkRetriever().getCard(bookmark.url(), bookmarkSessionStore.getBookmarkEx(bookmark)));
		return "bookmarks/fragment/card";
	}

	@PostMapping("/reload")
	@ResponseBody
	public String reload(HttpServletResponse response, Model model) {
		bookmarkService.reload();
		response.setHeader("HX-Trigger", "bookmarksChanged");
		return "";
	}

	@PostMapping(path = "/bookmark", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	@ResponseBody
	public String addBookmark(@RequestParam(name = "bm-url") String bmUrl, HttpServletResponse response) {
		bookmarkService.loadBookmarksIntoSessionIfNecessary();
		var newLine = bmUrl + ";todo" + System.lineSeparator();
		var csv = newLine + bookmarkSessionStore.getBookmarksCSV();
		bookmarkService.reCreateBookmarks(csv);
		bookmarkSessionStore.setPreviewBookmark(null);

		response.setHeader("HX-Trigger", "bookmarksChanged, newPreview");
		response.setStatus(HttpStatus.CREATED.value());
		return "";
	}

}
