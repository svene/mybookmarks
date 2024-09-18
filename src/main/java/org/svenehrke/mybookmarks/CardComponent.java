package org.svenehrke.mybookmarks;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;

import java.math.BigInteger;

@ViewComponent
public class CardComponent {


	public record Ctx(CardModel cardModel) implements ViewContext {}


	public Ctx render(CardModel cardModel) {
		return new Ctx(cardModel);
	}

	public record CardModel(Card card) {
		public static CardModel build(BookmarkService bookmarkService, BookmarkSessionStore bookmarkSessionStore, BigInteger id) {
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
			return new CardModel(card);
		}
	}
}
