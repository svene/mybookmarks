package org.svenehrke.mybookmarks.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;
import org.svenehrke.mybookmarks.service.BookmarkSessionStore;
import org.svenehrke.mybookmarks.service.BookmarkUtil;

import java.math.BigInteger;

@Controller
@AllArgsConstructor
@Slf4j
public class InlineEditingController {
	private final BookmarkSessionStore bookmarkSessionStore;
	private final BookmarkSessionService bookmarkSessionService;


	@PutMapping("/edit/inline/putbookmark")
	public RedirectView putBookmark(
		HttpServletRequest request,
		@RequestParam BigInteger id,
		@RequestParam String tags
	) {
		var bookmarks = bookmarkSessionService.getBookmarks();
		var newBookmarks = bookmarks.stream()
			.map(it -> it.id().equals(id) ?
				it.withTags(BookmarkUtil.tagStringToList(tags))
				: it).toList();
		bookmarkSessionStore.setBookmarks(newBookmarks);

		return BMControllerFunctions.redirect("/redirect/card/" + id, request);
	}
}
