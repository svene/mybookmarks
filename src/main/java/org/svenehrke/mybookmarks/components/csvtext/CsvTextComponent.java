package org.svenehrke.mybookmarks.components.csvtext;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.svenehrke.mybookmarks.service.BookmarkService;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;
import org.svenehrke.mybookmarks.service.BookmarkSessionStore;

@ViewComponent
@RequiredArgsConstructor
public class CsvTextComponent {
	private final BookmarkSessionStore bookmarkSessionStore;
	private final BookmarkService bookmarkService;
	private final BookmarkSessionService bookmarkSessionService;

	public record Ctx(String csv) implements ViewContext {}


	public ViewContext render() {
		bookmarkSessionService.loadBookmarksIntoSessionIfNecessary();
		var s = bookmarkService.convertBookmarksToCSV(bookmarkSessionStore.getBookmarks());
		return new Ctx(s);
	}
}
