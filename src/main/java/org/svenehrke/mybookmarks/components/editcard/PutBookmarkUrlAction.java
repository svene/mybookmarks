package org.svenehrke.mybookmarks.components.editcard;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

import java.math.BigInteger;

@ViewComponent
@Controller
@AllArgsConstructor
@Slf4j
public class PutBookmarkUrlAction {

	public static final String URL = "/bookmark/{id}/url";
	private static final UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromPath(URL);

	public final BookmarkSessionService bookmarkSessionService;

	public record Ctx(BookmarkSessionService bookmarkSessionService, BigInteger id) implements ViewContext {
	}

	public static String url(BigInteger id) {
		return urlBuilder.buildAndExpand(id).toUriString();
	}
	@PutMapping(URL)
	public Ctx doit(
		@PathVariable BigInteger id,
		@RequestParam String url
	) {
		bookmarkSessionService.setBookmarkUrl(id, url);
		return new Ctx(bookmarkSessionService, id);
	}

}
