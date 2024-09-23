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

	public Bookmark getById(BigInteger id, List<Bookmark> bookmarks) {
		return bookmarks
			.stream()
			.filter(it -> id.equals(it.id()))
			.findFirst()
			.orElse(
				BookmarkBuilder.builder()
					.id(BigInteger.ZERO)
					.url("https://www.heise.de")
					.tags(Collections.emptyList())
					.build()
			);
	}

	public String reload() {
		return new InitialDataLoader().readCsvAsString();
	}

	public BookmarkEx createBookmarkEx(Bookmark bookmark) {
		return new BookmarkRetriever().buildBookmarkEx(bookmark);
	}

	public Bookmark newPreviewBookmark(String bmUrl) {
		return BookmarkBuilder.builder()
			.id(BigInteger.valueOf(1L))
			.url(bmUrl)
			.tags(List.of("todo"))
			.build();
	}

	public String convertBookmarksToCSV(List<Bookmark> bookmarks) {
		StringBuilder sb = new StringBuilder();
		bookmarks.forEach(it -> {
			sb.append(it.url() + ";" + String.join(",", it.tags()) + System.lineSeparator());
		});
		return sb.toString();
	}

}
