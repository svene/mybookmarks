package org.svenehrke.mybookmarks;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ViewComponent
@RequiredArgsConstructor
public class BookmarkRowsComponent {
	private final BookmarkSessionStore bookmarkSessionStore;
	private final BookmarkSessionService bookmarkSessionService;

	public record Ctx(List<Bookmark> bookmarks) implements ViewContext {}

	public ViewContext render() {
		String searchTags = bookmarkSessionStore.getSearchTags();
		return new Ctx(bookmarkSessionService.findAllByTag(searchTags));
	}

}
