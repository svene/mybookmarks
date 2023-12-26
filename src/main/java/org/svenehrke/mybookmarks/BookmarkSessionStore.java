package org.svenehrke.mybookmarks;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@SessionScope
@Component
@Data
public class BookmarkSessionStore {
	private String bookmarksCSV;
	private CsvInfo bookmarksCsvInfo;
	private List<Bookmark> bookmarks = new LinkedList<>();
	private Bookmark previewBookmark;
	private Map<String, BookmarkEx> bookmarkExs = Collections.synchronizedMap(new HashMap<>());
	private String searchTags;
	private List<String> tags;

	public void handleNewCsvString(String csv) {
		setBookmarksCSV(csv);
		CsvInfo csvInfo = new CsvReader().getCsvInfo(csv);
		setBookmarksCsvInfo(csvInfo);
		List<Bookmark> newBookmarks = new CsvReader().convertCsvToBookmarks(csvInfo.records());
		setBookmarks(newBookmarks);
		List<String> tags = newBookmarks.stream()
			.flatMap(it -> it.tags().stream())
			.distinct()
			.sorted()
			.toList();
		setTags(tags);
	}

	public BookmarkEx getBookmarkEx(Bookmark bm) {
		return bookmarkExs.get(bm.url());
	}

}
