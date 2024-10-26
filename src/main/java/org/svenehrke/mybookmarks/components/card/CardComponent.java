package org.svenehrke.mybookmarks.components.card;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.svenehrke.mybookmarks.model.Bookmark;
import org.svenehrke.mybookmarks.model.BookmarkEx;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;
import org.svenehrke.mybookmarks.service.BookmarkUtil;

import java.math.BigInteger;

@ViewComponent
@RequiredArgsConstructor
@Controller
public class CardComponent {
	private final BookmarkSessionService bookmarkSessionService;

	public record Ctx(
		BigInteger id,
		Bookmark bookmark,
		BookmarkEx ogInfo,
		String tagsString
	) implements ViewContext {}

	public Ctx ctx(BigInteger id) {
		var bookmark = bookmarkSessionService.getById(id);
		return new Ctx(
			id,
			bookmark,
			bookmarkSessionService.getBookmarkEx(bookmark),
			BookmarkUtil.toTagsString(bookmark.tags())
		);
	}

	@GetMapping("/card/{id}")
	public Ctx card_id(@PathVariable BigInteger id) {
		return ctx(id);
	}

}
