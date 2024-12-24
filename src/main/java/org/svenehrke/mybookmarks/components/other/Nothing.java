package org.svenehrke.mybookmarks.components.other;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;

@ViewComponent
public class Nothing {
	public record Ctx() implements ViewContext {}
}
