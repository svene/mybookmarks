package org.svenehrke.mybookmarks.service;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import org.svenehrke.mybookmarks.model.Bookmark;
import org.svenehrke.mybookmarks.model.BookmarkEx;

import java.util.*;

@SessionScope
@Component
@Data
public class BookmarkSessionStore {
	private String bookmarksCSV;
	private Bookmark previewBookmark;
	// TODO: attach BookmarkEx to Bookmark and remove this map:
	private Map<String, BookmarkEx> bookmarkExs = Collections.synchronizedMap(new HashMap<>());
	private Map<String, TagSelection> tagsWithSelection = new HashMap<>();
	private BookmarkService.CsvParseResult csvParseResult = new BookmarkService.CsvParseResult(
		null, Collections.emptyList(), Collections.emptyList(), Collections.emptyMap()
	);

	public enum TagSelection {
		INCLUDE, EXCLUDE
	}
	public record TagWithSelection(String tag, TagSelection selection) {}

}
