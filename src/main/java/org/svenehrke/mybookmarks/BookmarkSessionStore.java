package org.svenehrke.mybookmarks;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@SessionScope
@Component
@Data
public class BookmarkSessionStore {
	private String bookmarksCSV;
	private CsvInfo bookmarksCsvInfo;
	private List<Bookmark> bookmarks = new LinkedList<>();
	private Bookmark previewBookmark;
	private Map<String, BookmarkEx> bookmarkExs = new HashMap<>();

	public void handleNewCsvString(String csv) {
		setBookmarksCSV(csv);
		CsvInfo csvInfo = new CsvReader().getCsvInfo(csv);
		setBookmarksCsvInfo(csvInfo);
		setBookmarks(new CsvReader().convertCsvToBookmarks(csvInfo.records()));
		updateExs();
	}

	private void updateExs() {
		getBookmarks().forEach(it -> {
			bookmarkExs.computeIfAbsent(
				it.url(),
				k -> new BookmarkRetriever().buildBookmarkEx(it)
			);
		});
	}

	public BookmarkEx getBookmarkEx(Bookmark bm) {
		return bookmarkExs.get(bm.url());
	}

}
