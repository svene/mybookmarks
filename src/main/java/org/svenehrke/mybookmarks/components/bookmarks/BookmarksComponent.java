package org.svenehrke.mybookmarks.components.bookmarks;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.svenehrke.mybookmarks.components.bookmarkrows.BookmarkRowsComponent;
import org.svenehrke.mybookmarks.components.csvtext.CsvTextComponent;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

import java.util.List;

@ViewComponent
@RequiredArgsConstructor
public class BookmarksComponent {

	public static final String URL = "/bookmarks";

	public final CsvTextComponent csvTextComponent;
	private final BookmarkSessionService bookmarkSessionService;
	private final BookmarkRowsComponent bookmarkRowsComponent;

	public record Ctx(
		BookmarksComponent ME,
		List<String> existingTags,
		BookmarkRowsComponent.Ctx bookmarkRowsCtx
	) implements ViewContext {}

	public Ctx render() {
		return new Ctx(
			this,
			bookmarkSessionService.getTags(),
			bookmarkRowsComponent.ctx()
		);
	}

}
