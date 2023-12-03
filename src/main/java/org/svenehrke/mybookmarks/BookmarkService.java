package org.svenehrke.mybookmarks;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class BookmarkService {
	private final BookmarkSessionStore bookmarkSessionStore;

	public void reCreateBookmarks(String csv) {
		bookmarkSessionStore.handleNewCsvString(csv);
	}

	public Bookmark getById(BigInteger id, List<Bookmark> bookmarks) {
		return bookmarks
			.stream()
			.filter(it -> id.equals(it.id()))
			.findFirst()
			.orElse(new Bookmark(BigInteger.ZERO, "https://www.heise.de", Collections.emptyList())
			);
	}

	public void loadBookmarksIntoSessionIfNecessary() {
		var bookmarks = bookmarkSessionStore.getBookmarks();
		if (bookmarks == null || bookmarks.isEmpty()) {
			reload();
		}
	}

	public void reload() {
		String csv = new InitialDataLoader().readCsvAsString();
		bookmarkSessionStore.handleNewCsvString(csv);
	}


	public List<Bookmark> findByTag(String tag) {
		loadBookmarksIntoSessionIfNecessary();
		var bookmarks = switch (tag) {
			case String s when StringUtils.hasLength(s) -> bookmarkSessionStore.getBookmarks().subList(0, 1);
			case null, default -> bookmarkSessionStore.getBookmarks();
		};
		return bookmarks;
	}

}
