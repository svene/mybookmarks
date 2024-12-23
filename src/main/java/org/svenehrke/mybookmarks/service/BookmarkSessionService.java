package org.svenehrke.mybookmarks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.svenehrke.mybookmarks.model.Bookmark;
import org.svenehrke.mybookmarks.model.BookmarkEx;
import org.svenehrke.mybookmarks.model.CsvInfo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class BookmarkSessionService {
	private final BookmarkService bookmarkService;
	private final BookmarkSessionStore bookmarkSessionStore;

	public BookmarkSessionStore store() {
		return bookmarkSessionStore;
	}

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
	public void addTagToBookmark(BigInteger id, String tag) {
		Bookmark bookmark = getById(id);
		var newTags = BookmarkUtil.toTagsString(bookmark.tags()) + "," + tag;
		setBookmarkTags(id, newTags);
	}
	public void removeTagFromBookmark(BigInteger id, String tag) {
		Bookmark bookmark = getById(id);
		if (bookmark.tags().size() <= 1) {
			return;
		}
		var newTags = bookmark.tags().stream().filter(it -> !it.equals(tag)).toList();
		setBookmarkTags(id, BookmarkUtil.toTagsString(newTags));
	}
	public void setBookmarkUrl(BigInteger id, String url) {
		var bm = getById(id);
		putBookmark(id, url, BookmarkUtil.toTagsString(bm.tags()));
	}
	public void setBookmarkTags(BigInteger id, String tags) {
		var bm = getById(id);
		putBookmark(id, bm.url(), tags);
	}
	public void putBookmark(BigInteger id, String url, String tags) {
		CsvInfo csvInfo = new CsvReader().getCsvInfo(store().getBookmarksCSV());
		var records = new ArrayList<>(csvInfo.records());
		records.set(id.intValue(), url + ";" + tags);
		handleNewCsvString(String.join(System.lineSeparator(), records));
	}
	public List<String> getFilteredTags(Predicate<Map.Entry<String, BookmarkSessionStore.TagSelection>> entryPredicate) {
		var tags = store().getTagsWithSelection();
		var result = tags.entrySet().stream()
			.filter(entryPredicate)
			.map(Map.Entry::getKey)
			.toList();
		return result;
	}

	public static final Predicate<Map.Entry<String, BookmarkSessionStore.TagSelection>> EXCLUDED_TAGS_PREDICATE =
		it -> it.getValue() == BookmarkSessionStore.TagSelection.EXCLUDE;
	public static final Predicate<Map.Entry<String, BookmarkSessionStore.TagSelection>> INCLUDED_TAGS_PREDICATE =
		it -> it.getValue() == BookmarkSessionStore.TagSelection.INCLUDE;
}
