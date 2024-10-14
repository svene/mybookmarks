package org.svenehrke.mybookmarks.components.existingtags;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

import java.util.List;

@ViewComponent
@RequiredArgsConstructor
public class ExistingTagsComponent {

	public final BookmarkSessionService bookmarkSessionService;

	public record Ctx(
		List<String> existingTags
	) implements ViewContext {}

	public Ctx ctx() {
		return new Ctx(bookmarkSessionService.getTags());
	}
}
