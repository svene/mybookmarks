package org.svenehrke.mybookmarks.components.bookmarkrows;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.svenehrke.mybookmarks.model.Bookmark;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;
import org.svenehrke.mybookmarks.service.BookmarkSessionStore;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ViewComponent
@RequiredArgsConstructor
@Controller
public class BookmarkRowsComponent {

	public record Ctx(BookmarkSessionService bookmarkSessionService) implements ViewContext {
		public List<Bookmark> buildBookmarks() {
			return findAllByTag();
		}
		private List<Bookmark> findAllByTag() {
			if (tags.isEmpty()) {
				return bookmarkSessionService.getCsvParseResult().bookmarks();
			}

			var exclusionTags = bookmarkSessionService.getFilteredTags(BookmarkSessionStore.TagSelection.EXCLUDE);
			var inclusionTags = bookmarkSessionService.getFilteredTags(BookmarkSessionStore.TagSelection.INCLUDE);
			return bookmarkSessionService.getCsvParseResult().bookmarks().stream()
				.filter(it -> inclusionTags.isEmpty() || !Collections.disjoint(it.tags(), inclusionTags))
				.filter(it -> exclusionTags.isEmpty() || it.tags().stream().noneMatch(exclusionTags::contains)) // Check that it.tags() does not contain any item from minusTags
				.collect(Collectors.toList());
		}


	}

}
