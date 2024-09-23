package org.svenehrke.mybookmarks.service;

import java.util.Arrays;
import java.util.List;

public class BookmarkUtil {
	public static List<String> tagStringToList(String tagsString) {
		return Arrays.asList(tagsString.split(","));
	}
}
