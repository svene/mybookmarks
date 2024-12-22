package org.svenehrke.mybookmarks.components.editcard;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;
import org.svenehrke.mybookmarks.service.BookmarkUtil;

import java.math.BigInteger;

@ViewComponent
@Controller
@RequiredArgsConstructor
@Slf4j
public class NewTagComponent {
	public static final String UI_URL = "/ui/bookmark/{id}/tags/new";
	private static final UriComponentsBuilder uiUrlBuilder = UriComponentsBuilder.fromPath(UI_URL);

	public static final String ADD_TAGS_URL = "/bookmark/{id}/tags/new";
	private static final UriComponentsBuilder addTagsUrlBuilder = UriComponentsBuilder.fromPath(ADD_TAGS_URL);

	public static String uiUrl(BigInteger id) {
		return uiUrlBuilder.buildAndExpand(id).toUriString();
	}
	public static String addTagsUrl(BigInteger id) {
		return addTagsUrlBuilder.buildAndExpand(id).toUriString();
	}

	public final BookmarkSessionService bookmarkSessionService;

	public record Ctx(BigInteger id) implements ViewContext {

	}

	@GetMapping(UI_URL)
	public Ctx ui(@PathVariable("id") BigInteger id) {
		return new Ctx(id);
	}

	@PostMapping(path = ADD_TAGS_URL, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public EditCardTagsComponent.Ctx addTags(
		@PathVariable("id") BigInteger id,
		@RequestParam String newtags,
		HttpServletResponse response
	) {
		log.info("Add tags to bookmark {}: {}", id, newtags);
		var tl = BookmarkUtil.tagsStringToList(newtags);

		bookmarkSessionService.addTagToBookmark(id, BookmarkUtil.toTagsString(tl));

		response.setHeader("HX-Trigger", "tags-changed"); // use comma separation for multiple events
		return new EditCardTagsComponent.Ctx(bookmarkSessionService, id, bookmarkSessionService.getById(id).tags()); // TODO: why: caller needs to pass tags
	}

}
