package org.svenehrke.mybookmarks.components.search;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

@ViewComponent
@Controller
@RequiredArgsConstructor
public class SearchComponent {

	public static final String URL = "/search/ui";

	public final BookmarkSessionService bookmarkSessionService;

	public record Ctx(BookmarkSessionService bookmarkSessionService) implements ViewContext {}

	@GetMapping(URL)
	public Ctx ctx() {
		return new Ctx(bookmarkSessionService);
	}

}
