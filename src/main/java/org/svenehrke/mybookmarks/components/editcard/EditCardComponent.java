package org.svenehrke.mybookmarks.components.editcard;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import gg.jte.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.svenehrke.mybookmarks.components.formcontent.FormContentComponent;
import org.svenehrke.mybookmarks.model.Bookmark;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;
import org.svenehrke.mybookmarks.service.BookmarkUtil;

import java.math.BigInteger;

@ViewComponent
@Controller
@RequiredArgsConstructor
public class EditCardComponent {

	public static final String COMPONENT_URL = "/edit/inline/form";

	public final BookmarkSessionService bookmarkSessionService;

	public record Ctx(EditCardComponent ME, BigInteger id) implements ViewContext {
		public FormContentComponent.Ctx formContentCtx(Content cancelButtonSlot) {
			Bookmark bookmark = ME.bookmarkSessionService.getById(id);
			return new FormContentComponent.Ctx(
				id, bookmark.url(), BookmarkUtil.toTagsString(bookmark.tags()),
				cancelButtonSlot
			);
		}
	}

	public Ctx ctx(BigInteger id) {
		return new Ctx(this, id);
	}

	@GetMapping(COMPONENT_URL)
	public Ctx ui(@RequestParam BigInteger id) {
		return ctx(id);
	}

}
