package org.svenehrke.mybookmarks.components.formcontent;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;

import java.math.BigInteger;

@ViewComponent
public class FormContentComponent {
	public record Ctx(BigInteger id, String url, String tagString) implements ViewContext {}

	public Ctx ctx(BigInteger id, String url, String tagString) {
		return new Ctx(id, url, tagString);
	}
}
