package org.svenehrke.mybookmarks.components.bookmarks;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.svenehrke.mybookmarks.components.addbookmark.AddBookmarkComponent;

@ViewComponent
@RequiredArgsConstructor
public class BookmarksComponent {

	public static final String URL = "/bookmarks";

	public record Ctx(AddBookmarkComponent.Ctx addBookmark) implements ViewContext {}

	public Ctx render() {
		return new Ctx(new AddBookmarkComponent.Ctx());
	}

}
