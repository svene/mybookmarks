package org.svenehrke.mybookmarks.service;

import java.util.Arrays;
import java.util.List;

public class BookmarkUtil {
	public static List<String> tagsStringToList(String tagsString) {
		return Arrays.stream(tagsString.split(",")).map(String::trim).toList();
	}
	public static String toTagsString(List<String> tags) {
		return String.join(",", tags);
	}
}
