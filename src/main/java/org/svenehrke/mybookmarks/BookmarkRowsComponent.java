package org.svenehrke.mybookmarks;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;

import java.util.List;

@ViewComponent
public class BookmarkRowsComponent {
	public record Ctx(List<Bookmark> bookmarks) implements ViewContext {}


	public Ctx render(List<Bookmark> bookmarks) {
		return new Ctx(bookmarks);
	}

}
