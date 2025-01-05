package org.svenehrke.mybookmarks.model;

import io.soabase.recordbuilder.core.RecordBuilder;

import java.net.URI;

@RecordBuilder
public record BookmarkEx(URI uri, String imageUrl, String title, String description) implements BookmarkExBuilder.With {

	public static BookmarkEx forException(Bookmark bm) {
		return BookmarkExBuilder.builder()
			.description(
				bm.url().length() < 50 ? bm.url() : bm.url().substring(0, 50) + "..."
			)
			.uri(URI.create(bm.url()))
			.imageUrl("https://placehold.co/1200x630/png?text=NONE")
			.build();
	}
}
