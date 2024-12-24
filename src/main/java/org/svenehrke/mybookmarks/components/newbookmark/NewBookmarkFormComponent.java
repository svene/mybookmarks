package org.svenehrke.mybookmarks.components.newbookmark;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.svenehrke.mybookmarks.components.image.ImageComponent;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

@ViewComponent
@RequiredArgsConstructor
@Controller
public class NewBookmarkFormComponent {

	public static final String URL = "/ui/bookmark/new";
	public static final String URL_CHANGED_URL = "/urlChanged";

	private final BookmarkSessionService bookmarkSessionService;

	public record Ctx(BookmarkSessionService bookmarkSessionService) implements ViewContext {}

	@GetMapping(URL)
	public Ctx ui() {
		return new Ctx(bookmarkSessionService);
	}

	@GetMapping(URL_CHANGED_URL)
	@ResponseBody
	public String urlchanged(@RequestParam String url, HttpServletResponse response) {
		if (!StringUtils.hasText(url)) {
			return "";
		}
		setPreviewBookmark(url);

		// Pattern: Example of event usage with HTMX:
		// (search for usages of ImageComponent.EVENT_URL_CHANGED to understand the associations)
		response.setHeader("HX-Trigger", ImageComponent.EVENT_URL_CHANGED);
		return "";
	}

	public void setPreviewBookmark(String bmUrl) {
		var bm = bookmarkSessionService.getById(BookmarkSessionService.PREVIEW_BM_ID).withUrl(bmUrl);
		bookmarkSessionService.store().setPreviewBookmark(bm);
	}

}
