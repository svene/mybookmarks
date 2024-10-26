package org.svenehrke.mybookmarks.components.image;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.svenehrke.mybookmarks.model.Bookmark;
import org.svenehrke.mybookmarks.model.BookmarkEx;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;
import org.svenehrke.mybookmarks.service.BookmarkSessionStore;

@ViewComponent
@RequiredArgsConstructor
@Controller
public class ImageComponent {

	private final BookmarkSessionService bookmarkSessionService;
	private final BookmarkSessionStore bookmarkSessionStore;

	public record Ctx(String url) implements ViewContext {}


	public Ctx ctx(String url) {
		return new Ctx(url);
	}

	public Ctx ctx() {
		Bookmark previewBookmark = bookmarkSessionStore.getPreviewBookmark();
		BookmarkEx ex = bookmarkSessionService.getBookmarkEx(previewBookmark);
		return new Ctx(ex.imageUrl());
	}

	@GetMapping("/image")
	public Ctx image() {
		return ctx();
	}


}
