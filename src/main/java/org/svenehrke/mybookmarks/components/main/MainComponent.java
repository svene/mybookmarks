package org.svenehrke.mybookmarks.components.main;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

@ViewComponent
@RequiredArgsConstructor
@Controller
public class MainComponent {

	public static final String URL = "/bookmarks";

	public final BookmarkSessionService bookmarkSessionService;

	public record Ctx(MainComponent ME) implements ViewContext {}
	public final Ctx ctx = new Ctx(this);

	@GetMapping("/")
	public RedirectView index() {
		return new RedirectView(URL);
	}

	@GetMapping(URL)
	public ViewContext ui() {
		return ctx;
	}
}
