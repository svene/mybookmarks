package org.svenehrke.mybookmarks.controller;

import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.svenehrke.mybookmarks.components.addbookmark.AddBookmarkComponent;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

@Controller()
@AllArgsConstructor
@Slf4j
public class NewBookmarkController {
	public static final String URL = "/bookmark";

	private final BookmarkSessionService bookmarkSessionService;
	private final AddBookmarkComponent addBookmarkComponent;

	/**
	 * NOTE:
	 * no Post/Redirect/Get Pattern is needed with htmx
	 * see https://htmx.org/docs/#response-headers:
	 * "Submitting a form via htmx has the benefit of
	 *  no longer needing the Post/Redirect/Get Pattern.
	 *  After successfully processing a POST request on the server,
	 *  you don’t need to return a HTTP 302 (Redirect).
	 *  You can directly return the new HTML fragment."
	 */
	@PostMapping(path = URL, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public ViewContext addBookmark(
		@RequestParam String url,
		HttpServletResponse response
	) {
		bookmarkSessionService.addBookmark(url);
		response.setHeader("HX-Trigger", "bookmarksChanged, newPreview");
		return addBookmarkComponent.render();
	}

}
