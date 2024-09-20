package org.svenehrke.mybookmarks;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@ViewComponent
@RequiredArgsConstructor
public class EditCardComponent {
	private final CardComponent cardComponent;
	private final FormContentComponent formContentComponent;

	public record Ctx(Card card, FormContentComponent.Ctx formContentComponent) implements ViewContext {}

	public ViewContext render(BigInteger id) {
		Card card = cardComponent.buildCard(id);
		return new Ctx(card, formContentComponent.render(card));
	}
	public ViewContext render(Card card) {
		return new Ctx(card, formContentComponent.render(card));
	}
}
