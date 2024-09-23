package org.svenehrke.mybookmarks.components.addbookmark;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;

@ViewComponent
@RequiredArgsConstructor
public class AddBookmarkComponent {

	public static final String URL = "/addbookmark";

	public record Ctx() implements ViewContext {}

	public Ctx render() {
		return new Ctx();
	}
}
