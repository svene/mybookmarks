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

	public record Ctx(CardComponent ME, BigInteger id) implements ViewContext {
		public Bookmark getBookmark() {
			return ME.bookmarkSessionService.getById(id);
		}
		public BookmarkEx getOgInfo() {
			return ME.bookmarkSessionService.getBookmarkEx(getBookmark());
		}
	}

	public Ctx ctx(BigInteger id) {
		return new Ctx(this, id);
	}


	@GetMapping("/card/{id}")
	public Ctx ui(@PathVariable BigInteger id) {
		return ctx(id);
	}

}
