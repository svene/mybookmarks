package org.svenehrke.mybookmarks.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.svenehrke.mybookmarks.model.BookmarkBuilder;
import org.svenehrke.mybookmarks.model.CsvInfo;
import org.svenehrke.mybookmarks.model.Bookmark;
import org.svenehrke.mybookmarks.model.BookmarkEx;

import java.math.BigInteger;
import java.util.Arrays;
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

	public record CsvParseResult(CsvInfo csvInfo, List<Bookmark> bookmarks, List<String> tags) {}

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
		return new CsvParseResult(csvInfo, newBookmarks, tags);
	}

	public record TagsStringParseResult(
		List<String> tags,
		List<String> plusTags,
		List<String> minusTags,
		List<String> normalTags
	) {}

	public TagsStringParseResult parseTagsString(String tagsString) {
		String[] split = tagsString.split(",");
		List<String> tags = Arrays.stream(split).map(String::trim).toList();
		List<String> plusTags = MishMash.filterList(tags, it -> it.startsWith("+")).stream().map(it -> it.substring(1)).toList();
		List<String> minusTags = MishMash.filterList(tags, it -> it.startsWith("-")).stream().map(it -> it.substring(1)).toList();
		List<String> normalTags = MishMash.filterList(tags, s -> !s.startsWith("+") && !s.startsWith("-"));

		return new TagsStringParseResult(tags, plusTags, minusTags, normalTags);
	}

	public String addUrlToCsv(String currentCsv, String bmUrl) {
		var newLine = bmUrl + ";anew" + System.lineSeparator(); // TODO: remove 'anew' (only for dev purposes)
		return newLine + currentCsv;
	}

}
