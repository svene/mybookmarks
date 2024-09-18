package org.svenehrke.mybookmarks;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;

@ViewComponent
@RequiredArgsConstructor
public class CsvTextComponent {
	private final BookmarkSessionStore bookmarkSessionStore;
	private final BookmarkService bookmarkService;

	public record Ctx(String csv) implements ViewContext {}


	public Ctx render() {
		bookmarkService.loadBookmarksIntoSessionIfNecessary();
		var s = bookmarkService.convertBookmarksToCSV(bookmarkSessionStore.getBookmarks());
		return new Ctx(s);
	}
}
