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
		String csv = getCsvAsString();
		return new ByteArrayInputStream(csv.getBytes());
	}

	public String getCsvAsString() {
		String csv = """
			https://www.freecodecamp.org/news/what-is-tailwind-css-a-beginners-guide/;read
			https://elanna.me/blog/2023/11/time-for-a-change-switching-to-analog;read
			https://www.youtube.com/watch?v=cgnrB5PkaBo;read
			https://htmx.org/essays/why-tend-not-to-use-content-negotiation/;read
			https://docs.localstack.cloud/academy/;read
			https://www.youtube.com/watch?v=h8Jth_ijZyY&t=1s;read
			https://thenewstack.io/how-to-use-databases-inside-github-actions/;read
			https://odrotbohm.de/2023/07/sliced-onion-architecture/;read
			https://programmingpercy.tech/blog/opengraph-protocol-how-and-why;sweng
			https://youtu.be/c7pgqHNTXQM?si=GnOyPeJzK_tBgp4c;sweng
			https://www.youtube.com/watch?v=qsk2JZT_vIc;sweng
			https://medium.com/@nuno.mt.sousa/part-ii-creating-a-kafka-cluster-test-extension-569ed750d137;sweng
			https://www.heise.de;news
			""";
		return csv;
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
