package org.svenehrke.mybookmarks.components.csvtext;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.svenehrke.mybookmarks.service.BookmarkService;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

@ViewComponent
@RequiredArgsConstructor
@Controller
public class CsvTextComponent {

	private final BookmarkService bookmarkService;
	private final BookmarkSessionService bookmarkSessionService;

	public record Ctx(String csv) implements ViewContext {}


	public Ctx ctx() {
		var csv = bookmarkService.convertBookmarksToCSV(bookmarkSessionService.getBookmarks());
		return new Ctx(csv);
	}

}
