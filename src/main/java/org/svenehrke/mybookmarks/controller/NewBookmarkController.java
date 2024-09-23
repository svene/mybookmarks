package org.svenehrke.mybookmarks.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

@Controller()
@AllArgsConstructor
@Slf4j
public class NewBookmarkController {
	public static final String BOOKMARK_URL = "/bookmark";

	private final BookmarkSessionService bookmarkSessionService;

	@PostMapping(path = BOOKMARK_URL, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public RedirectView addBookmark(
		@RequestParam String url,
		HttpServletRequest request,
		HttpServletResponse response
	) {
		bookmarkSessionService.addBookmark(url);
		response.setHeader("HX-Trigger", "bookmarksChanged, newPreview");
		return BMControllerFunctions.redirect("/redirect/card/" + 227, request);
	}

}
