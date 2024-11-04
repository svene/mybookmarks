package org.svenehrke.mybookmarks.components.settings;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.svenehrke.mybookmarks.components.csvtext.CsvTextComponent;

@ViewComponent
@Controller
@RequiredArgsConstructor
public class SettingsComponent {

	public static final String UI_URL = "/settings/ui";

	public final CsvTextComponent csvTextComponent;

	public record Ctx(SettingsComponent ME) implements ViewContext {}

	public Ctx ctx() {
		return new Ctx(this);
	}

	@GetMapping(UI_URL)
	public Ctx ui() {
		return ctx();
	}

}
