package org.svenehrke.mybookmarks.components.bookmarks;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.svenehrke.mybookmarks.components.csvtext.CsvTextComponent;
import org.svenehrke.mybookmarks.components.existingtags.ExistingTags;

@ViewComponent
@RequiredArgsConstructor
public class BookmarksComponent {

	public static final String URL = "/bookmarks";

	private final ExistingTags existingTags;
	private final CsvTextComponent csvTextComponent;

	public record Ctx(
		ExistingTags.Ctx existingTags,
		CsvTextComponent.Ctx csvText
	) implements ViewContext {}

	public Ctx render() {
		return new Ctx(
			existingTags.newContext(),
			csvTextComponent.render()
		);
	}

}
