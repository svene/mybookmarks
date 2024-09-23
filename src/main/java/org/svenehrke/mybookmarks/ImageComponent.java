package org.svenehrke.mybookmarks;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;

@ViewComponent
@RequiredArgsConstructor
public class ImageComponent {

	private final BookmarkService bookmarkService;
	private final BookmarkSessionStore bookmarkSessionStore;

	public record Ctx(String url) implements ViewContext {}


	public Ctx render(String url) {
		return new Ctx(url);
	}

	public Ctx render() {
		Bookmark previewBookmark = bookmarkService.getPreviewBookmark();
		BookmarkEx ex = bookmarkSessionStore.getBookmarkEx(previewBookmark);
		return new Ctx(ex.imageUrl());
	}

}
