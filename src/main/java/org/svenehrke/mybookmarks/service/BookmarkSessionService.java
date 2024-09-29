package org.svenehrke.mybookmarks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.svenehrke.mybookmarks.model.Bookmark;
import org.svenehrke.mybookmarks.model.BookmarkEx;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookmarkSessionService {
	private final BookmarkService bookmarkService;
	private final BookmarkSessionStore bookmarkSessionStore;

	public void createBookmarkExIfNecessary(Bookmark bookmark) {
		bookmarkSessionStore.getBookmarkExs().computeIfAbsent(
			bookmark.url(),
			(String url) -> bookmarkService.createBookmarkEx(bookmark)
		);

/*
		var ex = bookmarkSessionStore.getBookmarkExs().get(bookmark.url());
		if (ex == null) {
			ex = bookmarkService.createBookmarkEx(bookmark);
			bookmarkSessionStore.getBookmarkExs().putIfAbsent(bookmark.url(), ex);
		}
*/
	}

	public Bookmark getById(BigInteger id) {
		var result = bookmarkService.getById(id, getBookmarks());
		createBookmarkExIfNecessary(result);
		return result;
	}

	public BookmarkEx getBookmarkEx(Bookmark bm) {
		return bookmarkSessionStore.getBookmarkExs().get(bm.url());
	}

	public List<Bookmark> getBookmarks() {
		loadBookmarksIntoSessionIfNecessary();
		return bookmarkSessionStore.getBookmarks();
	}

	public List<String> getTags() {
		loadBookmarksIntoSessionIfNecessary();
		return bookmarkSessionStore.getTags();
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

	public List<Bookmark> findAllByTag(String tagsString) {
		if (!StringUtils.hasLength(tagsString)) {
			return getBookmarks();
		}

		var tags = bookmarkService.parseTagsString(tagsString);
		// Check that it.tags() does not contain any item from minusTags
		return getBookmarks().stream()
			.filter(it -> tags.normalTags().isEmpty() || !Collections.disjoint(it.tags(), tags.normalTags()))
			.filter(it -> tags.minusTags().isEmpty() || it.tags().stream().noneMatch(tags.minusTags()::contains)) // Check that it.tags() does not contain any item from minusTags
			.collect(Collectors.toList());

	}

	public void addBookmark(String bmUrl) {
		loadBookmarksIntoSessionIfNecessary();
		var csv = bookmarkService.addUrlToCsv(bookmarkSessionStore.getBookmarksCSV(), bmUrl);
		handleNewCsvString(csv);
		removePreviewBookmark();
	}

	public void handleNewCsvString(String csv) {
		bookmarkSessionStore.setBookmarksCSV(csv);
		var csvParseResult = bookmarkService.parse(csv);
		bookmarkSessionStore.setBookmarksCsvInfo(csvParseResult.csvInfo());
		bookmarkSessionStore.setBookmarks(csvParseResult.bookmarks());
		bookmarkSessionStore.setTags(csvParseResult.tags());
	}

	public void setPreviewBookmark(String bmUrl) {
		var previewBookmark = bookmarkService.newPreviewBookmark(bmUrl);
		bookmarkSessionStore.setPreviewBookmark(previewBookmark);
	}

	public void removePreviewBookmark() {
		bookmarkSessionStore.setPreviewBookmark(null);
	}

}
