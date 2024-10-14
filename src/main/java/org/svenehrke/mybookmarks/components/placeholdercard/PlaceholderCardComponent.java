package org.svenehrke.mybookmarks.components.placeholdercard;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import java.math.BigInteger;

@ViewComponent
@RequiredArgsConstructor
public class PlaceholderCardComponent {

	public record Ctx(BigInteger id) implements ViewContext {}

	public Ctx ctx(BigInteger id) {
		return new Ctx(id);
	}

}
