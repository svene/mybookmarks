package org.svenehrke.mybookmarks.components.newbookmark;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.svenehrke.mybookmarks.components.formcontent.FormContentComponent;
import org.svenehrke.mybookmarks.components.image.ImageComponent;
import org.svenehrke.mybookmarks.model.Bookmark;
import org.svenehrke.mybookmarks.model.BookmarkBuilder;
import org.svenehrke.mybookmarks.model.Card;
import org.svenehrke.mybookmarks.model.CardBuilder;

@ViewComponent
@RequiredArgsConstructor
public class NewBookmarkComponent {

	private final ImageComponent imageComponent;
	private final FormContentComponent formContentComponent;

	public record Ctx(
		ImageComponent.Ctx imageComponentContext,
		FormContentComponent.Ctx formContent
	) implements ViewContext {}

	public ViewContext render() {
		return new Ctx(
			imageComponent.render("https://placehold.co/640x336/png?text=PREVIEW..."),
			formContentComponent.render(buildNewBookmarkCard())
		);
	}

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

}
