package org.svenehrke.mybookmarks;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record Card(String url, String host, String ogImageUrl, String ogTitle, String ogDescription, String tagString) implements CardBuilder.With {
}
