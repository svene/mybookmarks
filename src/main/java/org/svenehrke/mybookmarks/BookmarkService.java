package org.svenehrke.mybookmarks;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
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
		synchronized (bookmarkSessionStore.getBookmarks()) {
			var bookmarks = bookmarkSessionStore.getBookmarks();
			if (bookmarks == null || bookmarks.isEmpty()) {
				reload();
			}
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
			List<String> plusTags = tagsHoldingPredicate(tags, it -> it.startsWith("+")).stream().map(it -> it.substring(1)).toList();
			List<String> minusTags = tagsHoldingPredicate(tags, it -> it.startsWith("-")).stream().map(it -> it.substring(1)).toList();
			List<String> normalTags = tagsHoldingPredicate(tags, s -> !s.startsWith("+") && !s.startsWith("-"));

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
		var previewBookmark = BookmarkBuilder.builder()
			.id(BigInteger.valueOf(1L))
			.url(bmUrl)
			.tags(List.of("todo"))
			.build();
		bookmarkSessionStore.setPreviewBookmark(previewBookmark);
	}

	public void ttt() {
		List<String> items = List.of("lkj");

	}

	private static List<String> tagsHoldingPredicate(List<String> tags, Predicate<String> stringPredicate) {
		return tags.stream()
			.filter(stringPredicate)
			.collect(Collectors.toList());
	}
}
