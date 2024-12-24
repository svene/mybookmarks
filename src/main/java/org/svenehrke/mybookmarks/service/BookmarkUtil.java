package org.svenehrke.mybookmarks.service;

import org.springframework.util.StringUtils;
import org.svenehrke.mybookmarks.model.Bookmark;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class BookmarkUtil {
	public static List<String> tagsStringToList(String tagsString) {
		return Arrays.stream(tagsString.split(",")).map(String::trim).toList();
	}
	public static String toTagsString(List<String> tags) {
		return String.join(",", tags);
	}
	public static List<String> concatTags(List<String> tags1, List<String> tags2) {
		return Stream.concat(tags1.stream(), tags2.stream()).toList();
	}
	public static boolean isBookmarkValid(Bookmark bookmark) {
		return StringUtils.hasText(bookmark.url());
	}
}
