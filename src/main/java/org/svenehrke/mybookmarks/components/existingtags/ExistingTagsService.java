package org.svenehrke.mybookmarks.components.existingtags;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExistingTagsService {

	private final BookmarkSessionService bookmarkSessionService;

	public List<String> getExistingTags() {
		var tagSet = new HashSet<String>();
		bookmarkSessionService.getBookmarks().forEach(bookmark -> {
			tagSet.addAll(bookmark.tags());

		});
		return tagSet.stream().toList();
	}

}
