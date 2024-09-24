package org.svenehrke.mybookmarks.components.bookmarkrows;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;
import org.svenehrke.mybookmarks.service.BookmarkSessionStore;
import org.svenehrke.mybookmarks.model.Bookmark;

import java.util.List;

@ViewComponent
@RequiredArgsConstructor
public class BookmarkRowsComponent {

	public static final String URL = "/bookmark_rows";

	private final BookmarkSessionStore bookmarkSessionStore;
	private final BookmarkSessionService bookmarkSessionService;

	public record Ctx(List<Bookmark> bookmarks) implements ViewContext {}

	public ViewContext render() {
		String searchTags = bookmarkSessionStore.getSearchTags();
		return new Ctx(bookmarkSessionService.findAllByTag(searchTags));
	}

}
