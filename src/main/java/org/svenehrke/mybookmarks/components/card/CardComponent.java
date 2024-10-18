package org.svenehrke.mybookmarks.components.card;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.svenehrke.mybookmarks.model.Bookmark;
import org.svenehrke.mybookmarks.model.BookmarkEx;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;
import org.svenehrke.mybookmarks.service.BookmarkUtil;

import java.math.BigInteger;

@ViewComponent
@RequiredArgsConstructor
public class CardComponent {
	private final BookmarkSessionService bookmarkSessionService;

	public record Ctx(
		BigInteger id,
		Bookmark bookmark,
		BookmarkEx ogInfo,
		String tagsString
	) implements ViewContext {}

	public ViewContext ctx(BigInteger id) {
		var bookmark = bookmarkSessionService.getById(id);
		return new Ctx(
			id,
			bookmark,
			bookmarkSessionService.getBookmarkEx(bookmark),
			BookmarkUtil.toTagsString(bookmark.tags())
		);
	}

}
