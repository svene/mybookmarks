package org.svenehrke.mybookmarks.service;

import org.svenehrke.mybookmarks.model.Bookmark;
import org.svenehrke.mybookmarks.model.BookmarkEx;
import org.svenehrke.mybookmarks.model.Card;
import org.svenehrke.mybookmarks.model.CardBuilder;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MishMash {

	public static Card getCard(Bookmark bm, BookmarkEx bmx) {
		return CardBuilder.builder()
			.id(bm.id())
			.url(bm.url())
			.host(bmx.uri().getHost())
			.ogImageUrl(bmx.imageUrl())
			.ogTitle(bmx.title())
			.ogDescription(bmx.description())
			.build();
	}

	public static List<String> filterList(
		List<String> items,
		Predicate<String> stringPredicate
	) {
		return items.stream()
			.filter(stringPredicate)
			.collect(Collectors.toList());
	}
}
