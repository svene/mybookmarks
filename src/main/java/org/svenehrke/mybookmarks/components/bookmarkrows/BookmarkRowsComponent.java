package org.svenehrke.mybookmarks.components.bookmarkrows;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.svenehrke.mybookmarks.components.placeholdercard.PlaceholderCardComponent;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;
import org.svenehrke.mybookmarks.service.BookmarkSessionStore;
import org.svenehrke.mybookmarks.model.Bookmark;

import java.util.List;

@ViewComponent
@RequiredArgsConstructor
@Controller
public class BookmarkRowsComponent {

	public static final String URL = "/bookmark_rows";

	private final BookmarkSessionStore bookmarkSessionStore;
	private final BookmarkSessionService bookmarkSessionService;
	public final PlaceholderCardComponent placeholderCardComponent;

	public record Ctx(BookmarkRowsComponent ME) implements ViewContext {}

	public Ctx ctx() {
		return new Ctx(this);
	}

	public List<Bookmark> buildBookmarks() {
		var bookmarks = bookmarkSessionService.findAllByTag(
			bookmarkSessionStore.getSearchTags()
		);
		return bookmarks;
	}

}
