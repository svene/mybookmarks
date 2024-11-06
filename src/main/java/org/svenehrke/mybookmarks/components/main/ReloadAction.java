package org.svenehrke.mybookmarks.components.main;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

@ViewComponent
@RequiredArgsConstructor
@Controller
public class ReloadAction {
	public static final String URL = "/reload";
	public final BookmarkSessionService bookmarkSessionService;

	public record Ctx(ReloadAction ME) implements ViewContext {}

	@PostMapping(URL)
	public Ctx doit() {
		bookmarkSessionService.reload();
		return new Ctx(this);
	}

}
