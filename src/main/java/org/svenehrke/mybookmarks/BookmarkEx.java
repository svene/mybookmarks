package org.svenehrke.mybookmarks;

import io.soabase.recordbuilder.core.RecordBuilder;

import java.net.URI;

@RecordBuilder
public record BookmarkEx(URI uri, String imageUrl, String title, String description) implements BookmarkExBuilder.With {
}
