package org.svenehrke.mybookmarks;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;

@ViewComponent
@RequiredArgsConstructor
public class NewBookmarkComponent {

	private final FormContentComponent formContentComponent;

	public record Ctx(FormContentComponent.Ctx formContent) implements ViewContext {}


	private Card buildNewBookmarkCard() {
		Bookmark bookmark = BookmarkBuilder.builder()
			.url("")
			.tags(java.util.List.of())
			.build();
		Card card = CardBuilder.builder()
			.url(bookmark.url())
			.tags(bookmark.tags())
			.build();
		return card;
	}

	public ViewContext render() {
		return new Ctx(formContentComponent.render(buildNewBookmarkCard()));
	}
}
