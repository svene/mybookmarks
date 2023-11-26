package org.svenehrke.mybookmarks;

import io.soabase.recordbuilder.core.RecordBuilder;

import java.math.BigInteger;
import java.util.List;

@RecordBuilder
public record Bookmark(BigInteger id, String url, List<String> tags) {
}
