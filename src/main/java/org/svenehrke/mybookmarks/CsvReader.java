package org.svenehrke.mybookmarks;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Slf4j
public class CsvReader {
	@SneakyThrows
	public List<Bookmark> convertCsvToBookmarks(String csv) {
		List<List<String>> records = new ArrayList<>();
		try (Scanner scanner = new Scanner(csv)) {
			while (scanner.hasNextLine()) {
				records.add(getRecordFromLine(scanner.nextLine()));
			}
		}
		log.debug("records = {0}", records);
		var csvBookmarks = io.vavr.collection.Stream
			.ofAll(records)
			.zipWithIndex()
			.map(it -> {
				var id = BigInteger.valueOf(Long.valueOf(it._2));
				String url = it._1.get(0);
				String tagsString = it._1.get(1);
				List<String> tags = Arrays.asList(tagsString.split(","));
				return new Bookmark(id, url, tags);
			})
			.toList()
			.asJava();

		return csvBookmarks;
	}
	private List<String> getRecordFromLine(String line) {
		List<String> values = new ArrayList<>();
		try (Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(";");
			while (rowScanner.hasNext()) {
				values.add(rowScanner.next());
			}
			return values;
		}
	}

}
