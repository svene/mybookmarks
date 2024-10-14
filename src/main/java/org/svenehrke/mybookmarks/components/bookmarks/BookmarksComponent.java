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

	private final CsvTextComponent csvTextComponent;
	private final BookmarkSessionService bookmarkSessionService;
	private final BookmarkRowsComponent bookmarkRowsComponent;

	public record Ctx(
		List<String> existingTags,
		CsvTextComponent.Ctx csvTextCtx,
		BookmarkRowsComponent.Ctx bookmarkRowsCtx
	) implements ViewContext {}

	public Ctx render() {
		return new Ctx(
			bookmarkSessionService.getTags(),
			csvTextComponent.ctx(),
			bookmarkRowsComponent.ctx()
		);
	}

}
