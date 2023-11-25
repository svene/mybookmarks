package org.svenehrke.mybookmarks;

import io.soabase.recordbuilder.core.RecordBuilder;

import java.math.BigInteger;

@RecordBuilder
public record Bookmark(BigInteger id, String url) {
}
