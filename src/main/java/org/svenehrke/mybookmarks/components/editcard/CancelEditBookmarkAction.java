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
	private static final UriComponentsBuilder urlBuilder =
		UriComponentsBuilder.fromPath(URL);

	public final BookmarkSessionService bookmarkSessionService;
	public record Ctx(CancelEditBookmarkAction ME, BigInteger id) implements ViewContext {}

	public static String url(BigInteger id) {
		return urlBuilder.buildAndExpand(id).toUriString();
	}

	@GetMapping(URL)
	public Ctx doit(@PathVariable BigInteger id) {
		return new Ctx(this, id);
	}

}
