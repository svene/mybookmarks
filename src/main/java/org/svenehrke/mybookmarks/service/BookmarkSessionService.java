package org.svenehrke.mybookmarks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.svenehrke.mybookmarks.model.Bookmark;
import org.svenehrke.mybookmarks.model.BookmarkEx;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class BookmarkSessionService {
	private final BookmarkService bookmarkService;
	private final BookmarkSessionStore bookmarkSessionStore;

	public Bookmark getById(BigInteger id) {
		return bookmarkService.getById(id, getCsvParseResult().bookmarks());
	}

	public BookmarkEx getBookmarkEx(Bookmark bm) {
		return bookmarkSessionStore.getBookmarkExs().computeIfAbsent(
			bm.url(),
			(String url) -> bookmarkService.createBookmarkEx(bm)
		);
	}

	public BookmarkService.CsvParseResult getCsvParseResult() {
		loadBookmarksIntoSessionIfNecessary();
		return bookmarkSessionStore.getCsvParseResult();
	}

	public void loadBookmarksIntoSessionIfNecessary() {
		synchronized (bookmarkSessionStore.getCsvParseResult().bookmarks()) {
			var bookmarks = bookmarkSessionStore.getCsvParseResult().bookmarks();
			if (bookmarks == null || bookmarks.isEmpty()) {
				reload();
			}
		}
	}

	public void reload() {
		handleNewCsvString(bookmarkService.reload());
	}

	public void handleNewCsvString(String csv) {
		bookmarkSessionStore.setBookmarksCSV(csv);
		bookmarkSessionStore.setCsvParseResult(bookmarkService.parse(csv));
	}

}
