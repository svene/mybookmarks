package org.svenehrke.mybookmarks;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;

@ViewComponent
@RequiredArgsConstructor
public class ImageComponent {

	private final BookmarkService bookmarkService;
	private final BookmarkSessionStore bookmarkSessionStore;

	public record Ctx(Card card) implements ViewContext {}


	public ViewContext render() {
		var card = getCard();
		return new Ctx(card);
	}

	private Card getCard() {
		Bookmark bm = bookmarkSessionStore.getPreviewBookmark();
		Card card;
		if (bm == null) {
			card = null;
		} else {
			bookmarkService.createBookmarkExIfNecessary(bm);
			card = new BookmarkRetriever().getCard(bm, bookmarkSessionStore.getBookmarkEx(bm));
		}
		return card;
	}
}
