package org.svenehrke.mybookmarks;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@ViewComponent
@RequiredArgsConstructor
public class EditCardComponent {
	private final CardComponent cardComponent;

	public record Ctx(Card card) implements ViewContext {}

	public ViewContext render(BigInteger id) {
		return new Ctx(cardComponent.buildCard(id));
	}
	public ViewContext render(Card card) {
		return new Ctx(card);
	}
}
