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

import java.math.BigInteger;

@ViewComponent
@RequiredArgsConstructor
@Controller
public class CardComponent {
	private final BookmarkSessionService bookmarkSessionService;

	public record Ctx(BookmarkSessionService bookmarkSessionService, BigInteger id) implements ViewContext {
		public Bookmark getBookmark() {
			return bookmarkSessionService.getById(id);
		}
		public BookmarkEx getOgInfo() {
			return bookmarkSessionService.getBookmarkEx(getBookmark());
		}
	}

	@GetMapping("/card/{id}")
	public Ctx ui(@PathVariable BigInteger id) {
		return new Ctx(bookmarkSessionService, id);
	}

}
