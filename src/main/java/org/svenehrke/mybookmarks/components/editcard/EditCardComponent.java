package org.svenehrke.mybookmarks.components.editcard;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.svenehrke.mybookmarks.components.formcontent.FormContentComponent;
import org.svenehrke.mybookmarks.model.Bookmark;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;
import org.svenehrke.mybookmarks.service.BookmarkUtil;

import java.math.BigInteger;

@ViewComponent
@RequiredArgsConstructor
public class EditCardComponent {

	public static final String URL = "/edit/inline/form";

	public final BookmarkSessionService bookmarkSessionService;

	public record Ctx(EditCardComponent ME, BigInteger id) implements ViewContext {}

	public Ctx ctx(BigInteger id) {
		return new Ctx(this, id);
	}

	public FormContentComponent.Ctx formContentCtx(BigInteger id) {
		Bookmark bookmark = bookmarkSessionService.getById(id);
		return new FormContentComponent.Ctx(
			id, bookmark.url(), BookmarkUtil.toTagsString(bookmark.tags())
		);
	}
}
