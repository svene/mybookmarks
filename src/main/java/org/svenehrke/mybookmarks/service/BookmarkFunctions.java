package org.svenehrke.mybookmarks.service;


import java.math.BigInteger;
import java.util.List;

public interface BookmarkFunctions {

	static List<String> availableTags(
		BookmarkSessionService bookmarkSessionService,
		BigInteger id
	) {
		var bm = bookmarkSessionService.getById(id);
		return bookmarkSessionService.getCsvParseResult().tags().stream()
			.filter(it -> !bm.tags().contains(it))
			.sorted()
			.toList();
	}

}
