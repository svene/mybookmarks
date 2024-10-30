package org.svenehrke.mybookmarks.components.formcontent;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import gg.jte.Content;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.svenehrke.mybookmarks.components.image.ImageComponent;
import org.svenehrke.mybookmarks.model.Bookmark;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;
import org.svenehrke.mybookmarks.service.BookmarkSessionStore;

import java.math.BigInteger;

@ViewComponent
@Controller
@AllArgsConstructor
public class FormContentComponent {
	public static final String URL_CHANGED = "/urlChanged";
	private final BookmarkSessionService bookmarkSessionService;
	private final BookmarkSessionStore bookmarkSessionStore;

	public record Ctx(
		BigInteger id,
		String url,
		String tagString,
		Content cancelButtonSlot
	) implements ViewContext {}

	public Ctx ctx(BigInteger id, String url, String tagString, Content cancelButtonSlot) {
		return new Ctx(id, url, tagString, cancelButtonSlot);
	}

	public Ctx ctx(Ctx ctx) {
		return ctx;
	}

	@GetMapping(URL_CHANGED)
	@ResponseBody
	public String urlchanged(@RequestParam String url, HttpServletResponse response) {
		bookmarkSessionService.setPreviewBookmark(url);
		Bookmark previewBookmark = bookmarkSessionStore.getPreviewBookmark();
		bookmarkSessionService.createBookmarkExIfNecessary(previewBookmark);

		// Example of event usage with HTMX:
		// (search for usages of ImageComponent.EVENT_URL_CHANGED to understand the associations)
		response.setHeader("HX-Trigger", ImageComponent.EVENT_URL_CHANGED);
		return "";
	}

}
