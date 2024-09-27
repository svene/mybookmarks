package org.svenehrke.mybookmarks.components.existingtags;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class ExistingTags {

	public static final String URL = "/existing_tags";

	private final BookmarkSessionService bookmarkSessionService;

	public record Ctx(java.util.List<String> tags) {}

	public Ctx newContext() {
		var tagSet = new HashSet<String>();
		bookmarkSessionService.getBookmarks().forEach(bookmark -> {
			tagSet.addAll(bookmark.tags());

		});
		return new Ctx(tagSet.stream().toList());
	}
}
