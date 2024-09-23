package org.svenehrke.mybookmarks;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

	public void handleNewCsvString(String csv) {
		bookmarkSessionStore.setBookmarksCSV(csv);
		CsvInfo csvInfo = new CsvReader().getCsvInfo(csv);
		bookmarkSessionStore.setBookmarksCsvInfo(csvInfo);
		List<Bookmark> newBookmarks = new CsvReader().convertCsvToBookmarks(csvInfo.records())
			.stream()
			.toList();
		bookmarkSessionStore.setBookmarks(newBookmarks);
		List<String> tags = newBookmarks.stream()
			.flatMap(it -> it.tags().stream())
			.distinct()
			.sorted()
			.toList();
		bookmarkSessionStore.setTags(tags);
	}

	public BookmarkEx getBookmarkEx(Bookmark bm) {
		return bookmarkSessionStore.getBookmarkExs().get(bm.url());
	}

	public void loadBookmarksIntoSessionIfNecessary() {
		synchronized (bookmarkSessionStore.getBookmarks()) {
			var bookmarks = bookmarkSessionStore.getBookmarks();
			if (bookmarks == null || bookmarks.isEmpty()) {
				reload();
			}
		}
	}

	public void reload() {
		handleNewCsvString(bookmarkService.reload());
	}

	public List<Bookmark> findByTag(String tagsString) {
		List<Bookmark> result;
		loadBookmarksIntoSessionIfNecessary();
		if (StringUtils.hasLength(tagsString)) {
			String[] split = tagsString.split(",");
			List<String> tags = Arrays.stream(split).map(String::trim).toList();
			List<String> plusTags = MishMash.filterList(tags, it -> it.startsWith("+")).stream().map(it -> it.substring(1)).toList();
			List<String> minusTags = MishMash.filterList(tags, it -> it.startsWith("-")).stream().map(it -> it.substring(1)).toList();
			List<String> normalTags = MishMash.filterList(tags, s -> !s.startsWith("+") && !s.startsWith("-"));

			result = bookmarkSessionStore.getBookmarks().stream()
				.filter(it -> normalTags.isEmpty() || !Collections.disjoint(it.tags(), normalTags))
				.filter(it -> minusTags.isEmpty() || it.tags().stream().noneMatch(minusTags::contains)) // Check that it.tags() does not contain any item from minusTags
				.collect(Collectors.toList());
		} else {
			result = bookmarkSessionStore.getBookmarks();
		}
		return result;
	}

	public void addBookmark(String bmUrl) {
		loadBookmarksIntoSessionIfNecessary();
		var newLine = bmUrl + ";anew" + System.lineSeparator(); // TODO: remove 'anew' (only for dev purposes)
		var csv = newLine + bookmarkSessionStore.getBookmarksCSV();
		reCreateBookmarks(csv);
		bookmarkSessionStore.setPreviewBookmark(null);
	}

	public void setPreviewBookmark(String bmUrl) {
		var previewBookmark = bookmarkService.newPreviewBookmark(bmUrl);
		bookmarkSessionStore.setPreviewBookmark(previewBookmark);
	}

	public void removePreviewBookmark() {
		bookmarkSessionStore.setPreviewBookmark(null);
	}

	private void reCreateBookmarks(String csv) {
		handleNewCsvString(csv);
	}
}
