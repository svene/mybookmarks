package org.svenehrke.mybookmarks;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class BookmarkService {
	private final BookmarkSessionStore bookmarkSessionStore;

	public void reload() {
	}

	public void reCreateBookmarks() {
		var csv = bookmarkSessionStore.getBookmarksCSV();
		List<Bookmark> bookmarks = new CsvReader().convertCsvToBookmarks(csv);
		bookmarkSessionStore.setBookmarks(bookmarks);
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
			String csv = new InitialDataLoader().readCsvAsString();
			bookmarkSessionStore.setBookmarksCSV(csv);
			bookmarkSessionStore.setBookmarks(new CsvReader().convertCsvToBookmarks(csv));
		}
	}

}
