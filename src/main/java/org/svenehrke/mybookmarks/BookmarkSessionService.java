package org.svenehrke.mybookmarks;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkSessionService {
	private final BookmarkService bookmarkService;
	private final BookmarkSessionStore bookmarkSessionStore;

	public void createBookmarkExIfNecessary(Bookmark bookmark) {
		var ex = bookmarkSessionStore.getBookmarkExs().get(bookmark.url());
		if (ex == null) {
			ex = bookmarkService.createBookmarkEx(bookmark);
			bookmarkSessionStore.getBookmarkExs().putIfAbsent(bookmark.url(), ex);
		}
	}
}
