package org.svenehrke.mybookmarks;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;

@ViewComponent
public class MessageComponent {
	public record Ctx(String message) implements ViewContext {}


	public ViewContext render() {
		return new Ctx("Hello World!");
	}
}
