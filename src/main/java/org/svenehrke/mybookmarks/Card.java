package org.svenehrke.mybookmarks;

import io.soabase.recordbuilder.core.RecordBuilder;

import java.math.BigInteger;
import java.util.List;

@RecordBuilder
public record Card(
	BigInteger id,
	String url,
	String host,
	String ogImageUrl,
	String ogTitle,
	String ogDescription,
	List<String> tags,
	String tagString
) implements CardBuilder.With {
}
