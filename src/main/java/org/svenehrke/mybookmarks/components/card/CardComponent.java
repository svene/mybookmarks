package org.svenehrke.mybookmarks.components.card;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;
import org.svenehrke.mybookmarks.model.Bookmark;
import org.svenehrke.mybookmarks.model.BookmarkEx;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

import java.math.BigInteger;

@ViewComponent
@RequiredArgsConstructor
@Controller
public class CardComponent {

	public static final String URL = "/card/{id}";
	public static final String REMOVE_TAG_URL = "/card/{id}/removetag";
	private static final UriComponentsBuilder uiUrlBuilder = UriComponentsBuilder.fromPath(URL);
	private static final UriComponentsBuilder removeTagUrlBuilder = UriComponentsBuilder.fromPath(REMOVE_TAG_URL);

	public static String uiUrl(BigInteger id) {
		return uiUrlBuilder.buildAndExpand(id).toUriString();
	}
	public static String removeTagUrl(BigInteger id) {
		return removeTagUrlBuilder.buildAndExpand(id).toUriString();
	}

	public static String cssCardSelector(BigInteger id) {
		return "bal-card[data-id=card-" + id + "]";
	}

	private final BookmarkSessionService bookmarkSessionService;

	public record Ctx(BookmarkSessionService bookmarkSessionService, BigInteger id) implements ViewContext {
		public Bookmark getBookmark() {
			return bookmarkSessionService.getById(id);
		}
		public BookmarkEx getOgInfo() {
			return bookmarkSessionService.getBookmarkEx(getBookmark());
		}
	}

	public static Ctx ctx(BookmarkSessionService bookmarkSessionService, BigInteger id) {
		return new Ctx(bookmarkSessionService, id);
	}
	@GetMapping(URL)
	public Ctx ctx(@PathVariable BigInteger id) {
		return new Ctx(bookmarkSessionService, id);
	}
	@PutMapping(REMOVE_TAG_URL)
	public Ctx remove_tag(
		@PathVariable("id") BigInteger id,
		@RequestParam(name = "tag") String tag
	) {
		bookmarkSessionService.removeTagFromBookmark(id, tag);
		return new Ctx(bookmarkSessionService, id);
	}

}
