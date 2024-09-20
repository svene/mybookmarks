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

	public record Ctx(FormContentComponent.Ctx formContent) implements ViewContext {}

	public ViewContext render(BigInteger id) {
		Card card = cardComponent.buildCard(id);
		return new Ctx(formContentComponent.render(card));
	}
}
