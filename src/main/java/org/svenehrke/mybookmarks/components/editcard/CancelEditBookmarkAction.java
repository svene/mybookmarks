package org.svenehrke.mybookmarks.components.editcard;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

import java.math.BigInteger;

@ViewComponent
@RequiredArgsConstructor
@Controller
public class CancelEditBookmarkAction {
	public static final String URL = "/canceleditbookmark/{id}";
	private static final UriComponentsBuilder uiUrlBuilder = UriComponentsBuilder.fromPath(URL);

	public final BookmarkSessionService bookmarkSessionService;
	public record Ctx(BookmarkSessionService bookmarkSessionService, BigInteger id) implements ViewContext {}

	public static String uiUrl(BigInteger id) {
		return uiUrlBuilder.buildAndExpand(id).toUriString();
	}

	@GetMapping(URL)
	public Ctx doit(@PathVariable BigInteger id) {
		return new Ctx(bookmarkSessionService, id);
	}

}
