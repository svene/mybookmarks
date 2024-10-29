package org.svenehrke.mybookmarks.components.newbookmark;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Smart Component
 */
@ViewComponent
@RequiredArgsConstructor
@Controller
public class CancelNewBookmarkAction {

	public record Ctx(CancelNewBookmarkAction ME) implements ViewContext {}
	public final Ctx ctx = new Ctx(this);

	public static final String URL = "/cancelnewbookmark";

	@GetMapping(path = URL)
	public ViewContext cancel_new_bookmark() {
		return ctx;
	}

}
