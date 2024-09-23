package org.svenehrke.mybookmarks;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;

@ViewComponent
@RequiredArgsConstructor
public class ExistingTagsComponent {
	private final BookmarkSessionStore bookmarkSessionStore;
	private final BookmarkSessionService bookmarkSessionService;
	private final BookmarkService bookmarkService;

	private final MessageComponent messageComponent;

	public record Ctx(java.util.List<String> tags, ViewContext messageComponent) implements ViewContext {}


	public ViewContext render() {
		bookmarkSessionService.loadBookmarksIntoSessionIfNecessary();
		var tagSet = new HashSet<String>();
		bookmarkSessionStore.getBookmarks().forEach(bookmark -> {
			tagSet.addAll(bookmark.tags());

		});
		return new Ctx(tagSet.stream().toList(), messageComponent.render());
	}
}
