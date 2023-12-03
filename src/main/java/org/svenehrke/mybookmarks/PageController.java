package org.svenehrke.mybookmarks;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@Slf4j
public class PageController {
	private final BookmarkService bookmarkService;
	private final BookmarkSessionStore bookmarkSessionStore;


	@GetMapping("/bookmarkspage")
	public String bookmarkspage(
		@RequestParam(required = false, name = "search_by_tags") String searchByTags,
		@RequestParam(required = false, name = "search_by_title") String searchByTitle,
		Model model
	) {
		model.addAttribute("bookmarks", bookmarkService.findByTag(searchByTags));
		String csv = bookmarkSessionStore.getBookmarksCSV();
		model.addAttribute("bookmarksCsv", csv);
		return "bookmarks/page/bookmarkspage";
	}


}
