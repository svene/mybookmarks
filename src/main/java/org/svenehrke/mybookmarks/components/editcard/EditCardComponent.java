package org.svenehrke.mybookmarks.components.editcard;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

import java.math.BigInteger;

@ViewComponent
@Controller
@RequiredArgsConstructor
public class EditCardComponent {

	public static final String UI_URL = "/edit/inline/form";

	public final BookmarkSessionService bookmarkSessionService;

	public record Ctx(BookmarkSessionService bookmarkSessionService, BigInteger id) implements ViewContext {}

	public Ctx ctx(BigInteger id) {
		return new Ctx(bookmarkSessionService, id);
	}

	@GetMapping(UI_URL)
	public Ctx ui(@RequestParam BigInteger id) {
		return ctx(id);
	}

}
