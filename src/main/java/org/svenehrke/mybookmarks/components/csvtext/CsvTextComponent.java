package org.svenehrke.mybookmarks.components.csvtext;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.svenehrke.mybookmarks.model.Bookmark;
import org.svenehrke.mybookmarks.service.BookmarkService;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;
import org.svenehrke.mybookmarks.service.BookmarkUtil;

import java.util.List;

@ViewComponent
@RequiredArgsConstructor
@Controller
public class CsvTextComponent {

	public final BookmarkService bookmarkService;
	public final BookmarkSessionService bookmarkSessionService;

	public record Ctx(CsvTextComponent ME) implements ViewContext {}

	public Ctx ctx() {
		return new Ctx(this);
	}

	public String convertBookmarksToCSV(List<Bookmark> bookmarks) {
		StringBuilder sb = new StringBuilder();
		bookmarks.forEach(it -> {
			sb.append(it.url() + ";" + BookmarkUtil.toTagsString(it.tags()) + System.lineSeparator());
		});
		return sb.toString();
	}

}
