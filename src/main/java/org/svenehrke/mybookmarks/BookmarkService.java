package org.svenehrke.mybookmarks;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
			.orElse(
				BookmarkBuilder.builder().id(BigInteger.ZERO).url("https://www.heise.de").tags(Collections.emptyList()).build()
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

	public void createBookmarkExIfNecessary(Bookmark bookmark) {
		var ex = bookmarkSessionStore.getBookmarkExs().get(bookmark.url());
		if (ex == null) {
			ex = new BookmarkRetriever().buildBookmarkEx(bookmark);
			bookmarkSessionStore.getBookmarkExs().putIfAbsent(bookmark.url(), ex);
		}
	}


	public List<Bookmark> findByTag(String tagsString) {
		List<Bookmark> result;
		loadBookmarksIntoSessionIfNecessary();
		if (StringUtils.hasLength(tagsString)) {
			String[] split = tagsString.split(",");
			List<String> tags = Arrays.stream(split).map(String::trim).toList();
			result = bookmarkSessionStore.getBookmarks().stream()
				.filter(it -> !Collections.disjoint(it.tags(), tags))
				.collect(Collectors.toList());
		} else {
			result = bookmarkSessionStore.getBookmarks();
		}
		return result;
	}

}
