package org.svenehrke.mybookmarks.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.svenehrke.mybookmarks.model.BookmarkBuilder;
import org.svenehrke.mybookmarks.model.CsvInfo;
import org.svenehrke.mybookmarks.model.Bookmark;
import org.svenehrke.mybookmarks.model.BookmarkEx;

import java.math.BigInteger;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class BookmarkService {

	/*
	 * TODO: make optional, split into Optional findById and Bookmark getById ?
	 */
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

	public record CsvParseResult(
		CsvInfo csvInfo,
		List<Bookmark> bookmarks,
		List<String> tags,
		Map<String, List<Bookmark>> groupbedByTag
	) {}

	public CsvParseResult parse(String csv) {
		CsvInfo csvInfo = new CsvReader().getCsvInfo(csv);
		List<Bookmark> newBookmarks = new CsvReader().convertCsvToBookmarks(csvInfo.records())
			.stream()
			.toList();
		List<String> tags = newBookmarks.stream()
			.flatMap(it -> it.tags().stream())
			.distinct()
			.sorted()
			.toList();
		var groupedByTag = newBookmarks.stream()
			.flatMap(bm -> bm.tags().stream().map(tag -> new AbstractMap.SimpleEntry<>(tag, bm)))
			.collect(Collectors.groupingBy(
				Map.Entry::getKey,
				Collectors.mapping(Map.Entry::getValue, Collectors.toList())
			));
		return new CsvParseResult(csvInfo, newBookmarks, tags, groupedByTag);
	}

}
