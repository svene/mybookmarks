package org.svenehrke.mybookmarks.components.main;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

import static org.svenehrke.mybookmarks.service.BookmarkSessionStore.TagSelection.EXCLUDE;
import static org.svenehrke.mybookmarks.service.BookmarkSessionStore.TagSelection.INCLUDE;


@ViewComponent
@RequiredArgsConstructor
@Controller
public class SearchByTagsAction {
	public static final String URL = "/search/tags";

	public final BookmarkSessionService bookmarkSessionService;

	public record Ctx(BookmarkSessionService bookmarkSessionService) implements ViewContext {

	}

	@PutMapping(URL)
	public Ctx searchTags(
		@RequestParam(required = false, name = "search_by_tags") String tag
	) {

		var x = bookmarkSessionService.store().getTagsWithSelection();
		x.compute(tag,
			(k, v) -> switch (v) {
				case null -> INCLUDE;
				case INCLUDE -> EXCLUDE;
				case EXCLUDE -> null;
			}
		);

		return new Ctx(bookmarkSessionService);
	}

}
