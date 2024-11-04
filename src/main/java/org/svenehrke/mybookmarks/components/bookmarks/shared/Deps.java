package org.svenehrke.mybookmarks.components.bookmarks.shared;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.svenehrke.mybookmarks.components.placeholdercard.PlaceholderCardComponent;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;
import org.svenehrke.mybookmarks.service.BookmarkSessionStore;

@Component
@RequiredArgsConstructor
public class Deps {
	public final BookmarkSessionStore bookmarkSessionStore;
	public final BookmarkSessionService bookmarkSessionService;
	public final PlaceholderCardComponent placeholderCardComponent;
}
