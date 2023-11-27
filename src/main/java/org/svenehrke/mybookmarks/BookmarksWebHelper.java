package org.svenehrke.mybookmarks;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookmarksWebHelper {
	public static final String KEY_BOOKMARKS_CSV = "bookmarks-csv";
	public static final String KEY_BOOKMARKS = "bookmarks";

	public String getCsvStringFromSession(HttpSession session) {
		String csv = (String) session.getAttribute(KEY_BOOKMARKS_CSV);
		return csv;
	}
	public List<Bookmark> getBookmarksFromSession(HttpSession session) {
		return  (List<Bookmark>) session.getAttribute(BookmarksWebHelper.KEY_BOOKMARKS);
	}

}
