package org.svenehrke.mybookmarks.components.formcontent;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import org.svenehrke.mybookmarks.model.Card;

@ViewComponent
public class FormContentComponent {
	public record Ctx(Card card) implements ViewContext {}


	public Ctx render(Card card) {
		return new Ctx(card);
	}
}
