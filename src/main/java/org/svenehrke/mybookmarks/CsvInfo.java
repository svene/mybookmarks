package org.svenehrke.mybookmarks;

import java.util.List;

public record CsvInfo(List<String> records, int maxCol) {
}
