package org.svenehrke.mybookmarks.actions.searchbytags;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.svenehrke.mybookmarks.components.bookmarkrows.BookmarkRowsComponent;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;
import org.svenehrke.mybookmarks.service.BookmarkSessionStore;

@ViewComponent
@RequiredArgsConstructor
@Controller
public class SearchByTagsAction {
	public static final String URL = "/reload";
	public final BookmarkSessionService bookmarkSessionService;
	public final BookmarkSessionStore bookmarkSessionStore;
	public final BookmarkRowsComponent bookmarkRowsComponent;

	public record Ctx(SearchByTagsAction ME) implements ViewContext {}
	public final Ctx ctx = new Ctx(this);

	// TODO: this is called when the user clicks on a tag widget. Not yet implemented correctly
	@PutMapping("/search/tags")
	public Ctx searchTags(
		@RequestParam(required = false, name = "search_by_tags") String searchByTags
	) {
		bookmarkSessionStore.setSearchTags(searchByTags);
		return ctx;
	}

}
