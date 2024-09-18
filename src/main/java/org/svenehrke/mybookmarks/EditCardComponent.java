package org.svenehrke.mybookmarks;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;

@ViewComponent
public class EditCardComponent {
	public record Ctx(CardComponent.CardModel cardModel) implements ViewContext {}


	public Ctx render(CardComponent.CardModel cardModel) {
		return new Ctx(cardModel);
	}
}
