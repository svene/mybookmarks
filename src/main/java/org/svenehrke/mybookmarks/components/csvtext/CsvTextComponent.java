package org.svenehrke.mybookmarks.components.csvtext;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;
import org.svenehrke.mybookmarks.service.BookmarkUtil;

@ViewComponent
@RequiredArgsConstructor
@Controller
public class CsvTextComponent {

	public final BookmarkSessionService bookmarkSessionService;

	public record Ctx(CsvTextComponent ME) implements ViewContext {
		public String convertBookmarksToCSV() {
			StringBuilder sb = new StringBuilder();
			ME.bookmarkSessionService.getCsvParseResult().bookmarks().forEach(it -> {
				sb.append(it.url() + ";" + BookmarkUtil.toTagsString(it.tags()) + System.lineSeparator());
			});
			return sb.toString();
		}
	}

	public Ctx ctx() {
		return new Ctx(this);
	}

}
