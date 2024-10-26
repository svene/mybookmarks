package org.svenehrke.mybookmarks.components.bookmarks;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.svenehrke.mybookmarks.components.bookmarkrows.BookmarkRowsComponent;
import org.svenehrke.mybookmarks.components.csvtext.CsvTextComponent;
import org.svenehrke.mybookmarks.components.existingtags.ExistingTagsComponent;

@ViewComponent
@RequiredArgsConstructor
@Controller
public class BookmarksComponent {

	public static final String URL = "/bookmarks";

	public final ExistingTagsComponent existingTagsComponent;
	public final CsvTextComponent csvTextComponent;
	public final BookmarkRowsComponent bookmarkRowsComponent;

	public record Ctx(BookmarksComponent ME) implements ViewContext {}
	public final Ctx ctx = new Ctx(this);

	@GetMapping(BookmarksComponent.URL)
	public ViewContext bookmarks() {
		return ctx;
	}
}
