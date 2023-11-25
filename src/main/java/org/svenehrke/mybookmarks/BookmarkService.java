package org.svenehrke.mybookmarks;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class BookmarkService {
	public static final List<Bookmark> BOOKMARKS = List.of(
		new Bookmark(BigInteger.valueOf(1), "https://programmingpercy.tech/blog/opengraph-protocol-how-and-why"),
		new Bookmark(BigInteger.valueOf(2), "https://programmingpercy.tech/blog/opengraph-protocol-how-and-why"),
		new Bookmark(BigInteger.valueOf(3), "https://www.heise.de"),
		new Bookmark(BigInteger.valueOf(4), "https://programmingpercy.tech/blog/opengraph-protocol-how-and-why"),
		new Bookmark(BigInteger.valueOf(5), "https://programmingpercy.tech/blog/opengraph-protocol-how-and-why"),
		new Bookmark(BigInteger.valueOf(6), "https://www.heise.de"),
		new Bookmark(BigInteger.valueOf(7), "https://programmingpercy.tech/blog/opengraph-protocol-how-and-why"),
		new Bookmark(BigInteger.valueOf(8), "https://programmingpercy.tech/blog/opengraph-protocol-how-and-why"),
		new Bookmark(BigInteger.valueOf(9), "https://www.heise.de"),
		new Bookmark(BigInteger.valueOf(10), "https://programmingpercy.tech/blog/opengraph-protocol-how-and-why"),
		new Bookmark(BigInteger.valueOf(11), "https://programmingpercy.tech/blog/opengraph-protocol-how-and-why"),
		new Bookmark(BigInteger.valueOf(12), "https://www.heise.de")
	);

	public List<Bookmark> getAllBookmarks() {
		return BOOKMARKS;
	}
	public Bookmark getById(BigInteger id) {
		return BOOKMARKS.stream().filter(it -> id.equals(it.id())).findFirst().orElse(new Bookmark(BigInteger.ZERO, "https://www.heise.de"));
	}
}
