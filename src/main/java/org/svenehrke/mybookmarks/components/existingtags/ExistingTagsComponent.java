package org.svenehrke.mybookmarks.components.existingtags;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

import java.util.HashSet;

@ViewComponent
@RequiredArgsConstructor
public class ExistingTagsComponent {
	private final BookmarkSessionService bookmarkSessionService;

	public record Ctx(java.util.List<String> tags) implements ViewContext {}


	public ViewContext render() {
		var tagSet = new HashSet<String>();
		bookmarkSessionService.getBookmarks().forEach(bookmark -> {
			tagSet.addAll(bookmark.tags());

		});
		return new Ctx(tagSet.stream().toList());
	}
}
