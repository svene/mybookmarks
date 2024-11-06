package org.svenehrke.mybookmarks.components.newbookmark;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

/**
 * Smart Component
 */
@ViewComponent
@RequiredArgsConstructor
@Controller
public class PostBookmarkAction {

	public record Ctx(PostBookmarkAction ME) implements ViewContext {}

	public static final String URL = "/bookmark";

	public final BookmarkSessionService bookmarkSessionService;


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
	public ViewContext doit(@RequestParam String url) {
		addBookmark(url);
		return new Ctx(this);
	}

	private void addBookmark(String bmUrl) {
		bookmarkSessionService.loadBookmarksIntoSessionIfNecessary();
		var csv = addUrlToCsv(bookmarkSessionService.store().getBookmarksCSV(), bmUrl);
		bookmarkSessionService.handleNewCsvString(csv);
		bookmarkSessionService.store().setPreviewBookmark(null);
	}

	private String addUrlToCsv(String currentCsv, String bmUrl) {
		var newLine = bmUrl + ";anew" + System.lineSeparator(); // TODO: remove 'anew' (only for dev purposes)
		return newLine + currentCsv;
	}

}
