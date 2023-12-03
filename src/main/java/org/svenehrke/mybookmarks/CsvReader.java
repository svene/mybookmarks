package org.svenehrke.mybookmarks;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Slf4j
public class CsvReader {

	public CsvInfo getCsvInfo(String csv) {
		List<String> records = new ArrayList<>();
		int maxLength = 0;
		try (Scanner scanner = new Scanner(csv)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				records.add(line);
				maxLength = Math.max(maxLength, line.length());
			}
		}
		return new CsvInfo(records, maxLength);
	}


	@SneakyThrows
	public List<Bookmark> convertCsvToBookmarks(List<String> lines) {
		List<List<String>> records = lines.stream()
			.map(this::getRecordFromLine)
			.collect(Collectors.toList());

		log.debug("records = {0}", records);
		var csvBookmarks = io.vavr.collection.Stream
			.ofAll(records)
			.reverse()
			.zipWithIndex()
			.map(it -> {
				var id = BigInteger.valueOf(Long.valueOf(it._2));
				String url = it._1.get(0);
				String tagsString = it._1.get(1);
				List<String> tags = Arrays.asList(tagsString.split(","));
				return new Bookmark(id, url, tags);
			})
			.reverse()
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
