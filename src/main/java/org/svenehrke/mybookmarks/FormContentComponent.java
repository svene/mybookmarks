package org.svenehrke.mybookmarks;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;

@ViewComponent
public class FormContentComponent {
	public record Ctx(Card card) implements ViewContext {}


	public Ctx render(Card card) {
		return new Ctx(card);
	}
}
