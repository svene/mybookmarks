package org.svenehrke.mybookmarks.components.existingtags;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

import java.util.Comparator;
import java.util.List;

import static org.svenehrke.mybookmarks.service.BookmarkSessionService.EXCLUDED_TAGS_PREDICATE;
import static org.svenehrke.mybookmarks.service.BookmarkSessionService.INCLUDED_TAGS_PREDICATE;

@ViewComponent
@Controller
@RequiredArgsConstructor
public class ExistingTagsComponent {

	public static final String URL = "/ui/existingtags";

	public final BookmarkSessionService bookmarkSessionService;

	public record TagAndCount(String tag, int count) {}
	public record Ctx(BookmarkSessionService bookmarkSessionService) implements ViewContext {
		public List<TagAndCount> existingTags() {
			return bookmarkSessionService.getCsvParseResult()
				.groupbedByTag().entrySet().stream()
				.sorted(Comparator.comparingInt(it -> it.getValue().size()))
				.map(it -> new TagAndCount(it.getKey(), it.getValue().size()))
				.toList()
				;
		}
		public String colorForTag(String tag) {
			var excludedTags = bookmarkSessionService.getFilteredTags(EXCLUDED_TAGS_PREDICATE);
			var includedTags = bookmarkSessionService.getFilteredTags(INCLUDED_TAGS_PREDICATE);
			String result;
			if (excludedTags.contains(tag)) {
				result = "red";
			} else {
				if (includedTags.contains(tag)) {
					result = "green";
				} else {
					result = "";
				}
			}
			return result;
		}
	}

	@GetMapping(URL)
	public Ctx ctx() {
		return new Ctx(bookmarkSessionService);
	}

}
