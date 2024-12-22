package org.svenehrke.mybookmarks.components.editcard;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

import java.math.BigInteger;

@ViewComponent
@Controller
@AllArgsConstructor
@Slf4j
public class PutBookmarkAction {

	public static final String URL = "/edit/inline/putbookmark";

	public final BookmarkSessionService bookmarkSessionService;

	public record Ctx(BookmarkSessionService bookmarkSessionService, BigInteger id) implements ViewContext {
		public Ctx putBookmark(String url, String tags) {
			bookmarkSessionService.putBookmark(id, url, tags);
			return this;
		}
	}

	@PutMapping(URL)
	public Ctx doit(
		@RequestParam BigInteger id,
		@RequestParam String url,
		@RequestParam String tags
	) {
		return new Ctx(bookmarkSessionService, id).putBookmark(url, tags);
	}

}
