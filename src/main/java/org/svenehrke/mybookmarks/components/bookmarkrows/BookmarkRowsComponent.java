package org.svenehrke.mybookmarks.components.bookmarkrows;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.svenehrke.mybookmarks.model.Bookmark;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.svenehrke.mybookmarks.service.BookmarkSessionService.EXCLUDED_TAGS_PREDICATE;
import static org.svenehrke.mybookmarks.service.BookmarkSessionService.INCLUDED_TAGS_PREDICATE;

@ViewComponent
@RequiredArgsConstructor
@Controller
public class BookmarkRowsComponent {

	public record Ctx(BookmarkSessionService bookmarkSessionService) implements ViewContext {
		public List<Bookmark> buildBookmarks() {
			return findAllByTag();
		}
		private List<Bookmark> findAllByTag() {

			var excludedTags = bookmarkSessionService.getFilteredTags(EXCLUDED_TAGS_PREDICATE);
			var includedTags = bookmarkSessionService.getFilteredTags(INCLUDED_TAGS_PREDICATE);
			if (excludedTags.isEmpty() && includedTags.isEmpty()) {
				return bookmarkSessionService.getCsvParseResult().bookmarks();
			}
			return bookmarkSessionService.getCsvParseResult().bookmarks().stream()
				.filter(it -> includedTags.isEmpty() || !Collections.disjoint(it.tags(), includedTags))
				.filter(it -> excludedTags.isEmpty() || it.tags().stream().noneMatch(excludedTags::contains)) // Check that it.tags() does not contain any item from minusTags
				.collect(Collectors.toList());
		}


	}

}
