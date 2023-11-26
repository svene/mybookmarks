package org.svenehrke.mybookmarks;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URI;
import java.util.*;
import java.util.function.Supplier;

@Slf4j
@Service
public class BookmarkService {
	private int mode = 0;
	private final String BM_MODE = System.getenv("BM_MODE");

	@SneakyThrows
	public String readCsvAsString() {
		Supplier<InputStream> inputStreamSupplier = switch (BM_MODE) {
			case "string" -> this::getCsvInputStreamFromString;
			case "local" -> this::getCsvInputStreamFromLocalFile;
			default -> this::getCsvInputStreamFromURL;
		};
		return new String(inputStreamSupplier.get().readAllBytes());
	}
	private InputStream getCsvInputStreamFromString() {
		String csv = """
			9;https://programmingpercy.tech/blog/opengraph-protocol-how-and-why
			9;https://programmingpercy.tech/blog/opengraph-protocol-how-and-why
			9;https://youtu.be/c7pgqHNTXQM?si=GnOyPeJzK_tBgp4c
			9;https://www.youtube.com/watch?v=qsk2JZT_vIc
			9;https://medium.com/@nuno.mt.sousa/part-ii-creating-a-kafka-cluster-test-extension-569ed750d137
			9;https://www.heise.de
			""";
		return new ByteArrayInputStream(csv.getBytes());
	}
	@SneakyThrows
	public InputStream getCsvInputStreamFromLocalFile() {
		return new FileInputStream("development/localonly/bookmarks.csv");
	}
	@SneakyThrows
	public InputStream getCsvInputStreamFromURL() {
		String bmUrl = System.getenv("BM_URL");
		URI uri = URI.create(bmUrl);
		var conn = uri.toURL().openConnection();
		return conn.getInputStream();
	}
	@SneakyThrows
	public List<Bookmark> readCsv(String csv) {
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

	public void reload() {
	}

	public Bookmark getById(BigInteger id, List<Bookmark> bookmarks) {
		return bookmarks
			.stream()
			.filter(it -> id.equals(it.id()))
			.findFirst()
			.orElse(new Bookmark(BigInteger.ZERO, "https://www.heise.de", Collections.emptyList())
			);
	}

}
