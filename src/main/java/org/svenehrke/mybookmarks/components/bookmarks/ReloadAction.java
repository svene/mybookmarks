package org.svenehrke.mybookmarks.components.bookmarks;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.svenehrke.mybookmarks.components.bookmarkrows.BookmarkRowsComponent;
import org.svenehrke.mybookmarks.components.csvtext.CsvTextComponent;
import org.svenehrke.mybookmarks.components.existingtags.ExistingTagsComponent;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

@ViewComponent
@RequiredArgsConstructor
@Controller
public class ReloadAction {
	public static final String URL = "/reload";
	public final BookmarkSessionService bookmarkSessionService;
	public final ExistingTagsComponent existingTagsComponent;
	public final BookmarkRowsComponent bookmarkRowsComponent;
	public final CsvTextComponent csvTextComponent;

	public record Ctx(ReloadAction ME) implements ViewContext {}
	public final Ctx ctx = new Ctx(this);

	@PostMapping(URL)
	public Ctx reload() {
		bookmarkSessionService.reload();
		return ctx;
	}

}
