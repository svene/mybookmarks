package org.svenehrke.mybookmarks.components.csvtext;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.svenehrke.mybookmarks.service.BookmarkService;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

@ViewComponent
@RequiredArgsConstructor
public class CsvTextComponent {

	public static final String URL = "/csv_textfield";

	private final BookmarkService bookmarkService;
	private final BookmarkSessionService bookmarkSessionService;

	public record Ctx(String csv) implements ViewContext {}


	public Ctx render() {
		var csv = bookmarkService.convertBookmarksToCSV(bookmarkSessionService.getBookmarks());
		return new Ctx(csv);
	}
}
