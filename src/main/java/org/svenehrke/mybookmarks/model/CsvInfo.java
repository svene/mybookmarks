package org.svenehrke.mybookmarks.model;

import java.util.List;

public record CsvInfo(List<String> records, int maxCol) {
}
