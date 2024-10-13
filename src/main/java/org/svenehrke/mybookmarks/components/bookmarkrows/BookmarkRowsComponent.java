package org.svenehrke.mybookmarks.components.bookmarkrows;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	private final PlaceholderCardComponent placeholderCardComponent;

	public record Ctx(
		List<Bookmark> bookmarks,
		PlaceholderCardComponent placeholderCardComponent
	) implements ViewContext {}

	public Ctx ctx() {
		String searchTags = bookmarkSessionStore.getSearchTags();
		return new Ctx(
			bookmarkSessionService.findAllByTag(searchTags),
			placeholderCardComponent
		);
	}

}
