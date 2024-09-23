package org.svenehrke.mybookmarks.components.card;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.svenehrke.mybookmarks.service.MishMash;
import org.svenehrke.mybookmarks.model.Bookmark;
import org.svenehrke.mybookmarks.model.Card;
import org.svenehrke.mybookmarks.service.BookmarkService;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

import java.math.BigInteger;

@ViewComponent
@RequiredArgsConstructor
public class CardComponent {
	private final BookmarkSessionService bookmarkSessionService;
	private final BookmarkService bookmarkService;

	public record Ctx(Card card) implements ViewContext {}

	public Card buildCard(BigInteger id) {
		var bookmarks = bookmarkSessionService.getBookmarks();
		Bookmark bookmark = bookmarkService.getById(id, bookmarks);

		bookmarkSessionService.createBookmarkExIfNecessary(bookmark);
		Card card = MishMash.getCard(
				bookmark,
				bookmarkSessionService.getBookmarkEx(bookmark)
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
