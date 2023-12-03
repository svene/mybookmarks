package org.svenehrke.mybookmarks;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.LinkedList;
import java.util.List;

@SessionScope
@Component
@Data
public class BookmarkSessionStore {
	private String bookmarksCSV;
	private CsvInfo bookmarksCsvInfo;
	private List<Bookmark> bookmarks = new LinkedList<>();

	public void handleNewCsvString(String csv) {
		setBookmarksCSV(csv);
		CsvInfo csvInfo = new CsvReader().getCsvInfo(csv);
		setBookmarksCsvInfo(csvInfo);
		setBookmarks(new CsvReader().convertCsvToBookmarks(csvInfo.records()));
	}

}
