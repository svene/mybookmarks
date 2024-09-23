package org.svenehrke.mybookmarks;

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
}
