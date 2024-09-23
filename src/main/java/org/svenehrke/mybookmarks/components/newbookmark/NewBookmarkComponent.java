package org.svenehrke.mybookmarks.components.newbookmark;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.svenehrke.mybookmarks.components.formcontent.FormContentComponent;
import org.svenehrke.mybookmarks.components.image.ImageComponent;
import org.svenehrke.mybookmarks.model.Bookmark;
import org.svenehrke.mybookmarks.model.BookmarkBuilder;
import org.svenehrke.mybookmarks.model.Card;
import org.svenehrke.mybookmarks.model.CardBuilder;
import org.svenehrke.mybookmarks.service.BookmarkService;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;
import org.svenehrke.mybookmarks.service.BookmarkSessionStore;
import org.svenehrke.mybookmarks.service.MishMash;

@ViewComponent
@RequiredArgsConstructor
public class NewBookmarkComponent {

	private final ImageComponent imageComponent;
	private final FormContentComponent formContentComponent;
	private final BookmarkSessionStore bookmarkSessionStore;
	private final BookmarkSessionService bookmarkSessionService;
	private final BookmarkService bookmarkService;

	public record Ctx(
		ImageComponent.Ctx imageComponentContext,
		FormContentComponent.Ctx formContent,
		String url
	) implements ViewContext {}

	public ViewContext render() {
		Card previewCard = getPreviewCard();
		return new Ctx(
			imageComponent.render("https://placehold.co/640x336/png?text=PREVIEW..."),
			formContentComponent.render(buildNewBookmarkCard()),
			(previewCard == null) ? "https://placehold.co/640x336/png?text=PREVIEW..." : previewCard.ogImageUrl()
		);
	}

	private Card buildNewBookmarkCard() {
		Bookmark bookmark = BookmarkBuilder.builder()
			.url("")
			.tags(java.util.List.of())
			.build();
		Card card = CardBuilder.builder()
			.url(bookmark.url())
			.tags(bookmark.tags())
			.build();
		return card;
	}

	private Card getPreviewCard() {
		Bookmark bm = bookmarkSessionStore.getPreviewBookmark();
		Card card;
		if (bm == null) {
			card = null;
		} else {
			bookmarkSessionService.createBookmarkExIfNecessary(bm);
			card = MishMash.getCard(bm, bookmarkSessionService.getBookmarkEx(bm));
		}
		return card;
	}
}
