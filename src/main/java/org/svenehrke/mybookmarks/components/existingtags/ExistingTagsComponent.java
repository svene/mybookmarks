package org.svenehrke.mybookmarks.components.existingtags;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

import java.util.Comparator;
import java.util.List;

@ViewComponent
@RequiredArgsConstructor
public class ExistingTagsComponent {

	public final BookmarkSessionService bookmarkSessionService;

	public record TagAndCount(String tag, int count) {}
	public record Ctx(List<TagAndCount> existingTags) implements ViewContext {}

	public Ctx ctx() {
		var lst = bookmarkSessionService.getCsvParseResult()
			.groupbedByTag().entrySet().stream()
			.sorted(Comparator.comparingInt(it -> it.getValue().size()))
			.map(it -> new TagAndCount(it.getKey(), it.getValue().size()))
			.toList()
			;
		return new Ctx(lst);
	}
}
