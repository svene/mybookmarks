package org.svenehrke.mybookmarks.components.editcard;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.svenehrke.mybookmarks.service.BookmarkFunctions;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;
import org.svenehrke.mybookmarks.htmx.HtmxResponseUtils;

import java.math.BigInteger;
import java.util.List;

import static org.svenehrke.mybookmarks.service.BookmarkUtil.EVENT_TAGS_CHANGED;

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

	public static Ctx ctx(BookmarkSessionService bookmarkSessionService, BigInteger id) {
		var bm = bookmarkSessionService.getById(id);
		return new Ctx(bookmarkSessionService, id, bm.tags());
	}

	@PutMapping(ADD_TAG_URL)
	public Ctx add_tag(
		@RequestParam BigInteger id,
		@RequestParam(name = "tag") String tag,
		HttpServletResponse response
	) {
		bookmarkSessionService.addTagToBookmark(id, tag);
		HtmxResponseUtils.setHxTrigger(response, EVENT_TAGS_CHANGED);
		return new Ctx(bookmarkSessionService, id, bookmarkSessionService.getById(id).tags());
	}
	@PutMapping(REMOVE_TAG_URL)
	public Ctx remove_tag(
		@RequestParam BigInteger id,
		@RequestParam(name = "tag") String tag,
		HttpServletResponse response
	) {
		bookmarkSessionService.removeTagFromBookmark(id, tag);
		HtmxResponseUtils.setHxTrigger(response, EVENT_TAGS_CHANGED);
		return new Ctx(bookmarkSessionService, id, bookmarkSessionService.getById(id).tags());
	}
}
