package org.svenehrke.mybookmarks;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@ViewComponent
@RequiredArgsConstructor
public class CardComponent {
	private final BookmarkSessionStore bookmarkSessionStore;
	private final BookmarkService bookmarkService;

	public record Ctx(Card card) implements ViewContext {}

	public Card buildCard(BigInteger id) {
		bookmarkService.loadBookmarksIntoSessionIfNecessary();
		var bookmarks = bookmarkSessionStore.getBookmarks();
		Bookmark bookmark = bookmarkService.getById(id, bookmarks);

		bookmarkService.createBookmarkExIfNecessary(bookmark);
		Card card = new BookmarkRetriever().getCard(
				bookmark,
				bookmarkSessionStore.getBookmarkEx(bookmark)
			)
			.withTags(bookmark.tags())
			.withTagString(String.join(",", bookmark.tags()));
		return card;
	}

	public ViewContext render(BigInteger id) {
		return render(buildCard(id));
	}

	public ViewContext render(Card card) {
		return new Ctx(card);
	}

}
