package org.svenehrke.mybookmarks.components.addbookmark;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@ViewComponent
@RequiredArgsConstructor
@Controller
public class AddBookmarkComponent {
	public record Ctx() implements ViewContext {}

	public static final String URL = "/ui/addbookmark";

	@GetMapping(path = URL)
	public ViewContext doit() {
		return new Ctx();
	}
}
