package org.svenehrke.mybookmarks.components.newbookmark;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.svenehrke.mybookmarks.components.formcontent.FormContentComponent;
import org.svenehrke.mybookmarks.components.image.ImageComponent;

import java.math.BigInteger;

@ViewComponent
@RequiredArgsConstructor
public class NewBookmarkComponent {

	public static final String URL = "/newbookmark/form";

	private final ImageComponent imageComponent;
	private final FormContentComponent formContentComponent;

	public record Ctx(
		ImageComponent.Ctx imageComponentContext,
		FormContentComponent.Ctx formContent
	) implements ViewContext {}

	public ViewContext render() {
		return new Ctx(
			imageComponent.render("https://placehold.co/640x336/png?text=PREVIEW..."),
			formContentComponent.render(BigInteger.ZERO, "", "")
		);
	}

}
