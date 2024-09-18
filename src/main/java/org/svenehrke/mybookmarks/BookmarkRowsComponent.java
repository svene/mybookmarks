package org.svenehrke.mybookmarks;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ViewComponent
@RequiredArgsConstructor
public class BookmarkRowsComponent {
	private final BookmarkSessionStore bookmarkSessionStore;
	private final BookmarkService bookmarkService;

	public record Ctx(List<Bookmark> bookmarks) implements ViewContext {}

	public Ctx render() {
		String searchTags = bookmarkSessionStore.getSearchTags();
		return new Ctx(bookmarkService.findByTag(searchTags));
	}

}
