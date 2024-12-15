package org.svenehrke.mybookmarks.components.editcard;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.svenehrke.mybookmarks.service.BookmarkFunctions;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

import java.math.BigInteger;
import java.util.List;

@ViewComponent
@RequiredArgsConstructor
@Controller
public class EditCardTagsComponent {
	public static final String ADD_TAG_URL = "/edit/inline/addtag";
	public static final String REMOVE_TAG_URL = "/edit/inline/removetag";

	public final BookmarkSessionService bookmarkSessionService;

	public record Ctx(
		BookmarkSessionService bookmarkSessionService,
		BigInteger id,
		List<String> tags
	) implements ViewContext {
		public List<String> availableTags() {
			return BookmarkFunctions.availableTags(bookmarkSessionService, id);
		}
	}

	@PutMapping(ADD_TAG_URL)
	public Ctx add_tag(
		@RequestParam BigInteger id,
		@RequestParam(name = "tag") String tag
	) {
		bookmarkSessionService.addTagToBookmark(id, tag);
		return new Ctx(bookmarkSessionService, id, bookmarkSessionService.getById(id).tags());
	}
	@PutMapping(REMOVE_TAG_URL)
	public Ctx remove_tag(
		@RequestParam BigInteger id,
		@RequestParam(name = "tag") String tag
	) {
		bookmarkSessionService.removeTagFromBookmark(id, tag);
		return new Ctx(bookmarkSessionService, id, bookmarkSessionService.getById(id).tags());
	}
}
