package org.svenehrke.mybookmarks.components.newbookmark;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.svenehrke.mybookmarks.components.image.ImageComponent;
import org.svenehrke.mybookmarks.components.other.Nothing;
import org.svenehrke.mybookmarks.htmx.HtmxResponseUtils;
import org.svenehrke.mybookmarks.htmx.HxSwapValues;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;
import org.svenehrke.mybookmarks.service.BookmarkUtil;

import java.util.List;

/**
 * Smart Component
 */
@ViewComponent
@RequiredArgsConstructor
@Controller
public class PostBookmarkAction {

	public record Ctx(BookmarkSessionService bookmarkSessionService) implements ViewContext {}

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
	@PostMapping(path = URL)
	public ViewContext doit(HttpServletResponse response) {
		var bm = bookmarkSessionService.store().getPreviewBookmark();
		if (!BookmarkUtil.isBookmarkValid(bm)) {
			HtmxResponseUtils.setHxReSwap(response, HxSwapValues.NONE);
			return new Nothing.Ctx();
		}
		addBookmark();
		return new Ctx(bookmarkSessionService);
	}

	private void addBookmark() {
		var bm = bookmarkSessionService.store().getPreviewBookmark();
		bookmarkSessionService.loadBookmarksIntoSessionIfNecessary();
		var csv = addUrlToCsv(bookmarkSessionService.store().getBookmarksCSV(), bm.url(), bm.tags());
		bookmarkSessionService.handleNewCsvString(csv);
		bookmarkSessionService.store().setPreviewBookmark(null);
	}

	private String addUrlToCsv(String currentCsv, String bmUrl, List<String> tags) {
		var newLine = bmUrl + ";" + BookmarkUtil.toTagsString(tags) + System.lineSeparator();
		return newLine + currentCsv;
	}

}
