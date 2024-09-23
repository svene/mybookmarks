package org.svenehrke.mybookmarks;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
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

}
