package org.svenehrke.mybookmarks.components.bookmarks;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.svenehrke.mybookmarks.components.addbookmark.AddBookmarkComponent;
import org.svenehrke.mybookmarks.components.existingtags.ExistingTagsComponent;

@ViewComponent
@RequiredArgsConstructor
public class BookmarksComponent {

	public static final String URL = "/bookmarks";

	private final AddBookmarkComponent addBookmarkComponent;
	private final ExistingTagsComponent existingTagsComponent;

	public record Ctx(
		AddBookmarkComponent.Ctx addBookmark,
		ExistingTagsComponent.Ctx existingTags
	) implements ViewContext {}

	public Ctx render() {
		return new Ctx(
			addBookmarkComponent.render(),
			existingTagsComponent.render()
		);
	}

}
