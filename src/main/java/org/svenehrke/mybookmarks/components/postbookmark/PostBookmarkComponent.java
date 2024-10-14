package org.svenehrke.mybookmarks.components.postbookmark;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.svenehrke.mybookmarks.components.bookmarkrows.BookmarkRowsComponent;
import org.svenehrke.mybookmarks.components.csvtext.CsvTextComponent;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

import java.util.List;

/**
 * Smart Component
 */
@ViewComponent
@RequiredArgsConstructor
@Controller
public class PostBookmarkComponent {

	public static final String URL = "/bookmark";

	private final BookmarkSessionService bookmarkSessionService;
	private final BookmarkRowsComponent bookmarkRowsComponent;
	private final CsvTextComponent csvTextComponent;

	public record Ctx(
		List<String> existingTags,
		BookmarkRowsComponent.Ctx bookmarkRowsCtx,
		CsvTextComponent.Ctx csvTextCtx
	) implements ViewContext {}

	public Ctx ctx() {
		return new Ctx(
			bookmarkSessionService.getTags(),
			bookmarkRowsComponent.ctx(),
			csvTextComponent.ctx()
		);
	}

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
	public ViewContext POST_bookmark(
		@RequestParam String url,
		HttpServletResponse response
	) {
		bookmarkSessionService.addBookmark(url);
		response.setHeader("HX-Trigger", "bookmarksChanged, newPreview");
		return ctx();
	}

}
