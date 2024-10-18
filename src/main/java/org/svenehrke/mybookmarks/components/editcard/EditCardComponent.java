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

	private final BookmarkSessionService bookmarkSessionService;
	private final FormContentComponent formContentComponent;

	public record Ctx(FormContentComponent.Ctx formContent) implements ViewContext {}

	public ViewContext render(BigInteger id) {
		Bookmark bookmark = bookmarkSessionService.getById(id);
		return new Ctx(formContentComponent.ctx(id, bookmark.url(), BookmarkUtil.toTagsString(bookmark.tags())));
	}
}
