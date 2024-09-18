package org.svenehrke.mybookmarks;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;

@ViewComponent
public class MessageComponent {
	public record MessageComponentCtx(String message) implements ViewContext {}


	public MessageComponentCtx render() {
		return new MessageComponentCtx("Hello World!");
	}
}
