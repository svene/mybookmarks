package org.svenehrke.mybookmarks;

import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.List;

@RecordBuilder
public record Card(
	String url,
	String host,
	String ogImageUrl,
	String ogTitle,
	String ogDescription,
	List<String> tags,
	String tagString
) implements CardBuilder.With {
}
