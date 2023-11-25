package org.svenehrke.mybookmarks;

import io.soabase.recordbuilder.core.RecordBuilder;

import java.math.BigInteger;

@RecordBuilder
public record Card(String url, String host, String ogImageUrl, String ogTitle, String ogDescription) {
}
