package org.svenehrke.mybookmarks;

import lombok.SneakyThrows;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.File;
import java.math.BigInteger;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class BookmarkService implements InitializingBean {
	private List<Bookmark> theBookmarks = new ArrayList<>();

	@Override
	public void afterPropertiesSet() throws Exception {
		readCsv();
	}
	@SneakyThrows
	public void readCsv() {
		List<List<String>> records = new ArrayList<>();
		try (Scanner scanner = new Scanner(new File("development/localonly/bookmarks.csv"))) {
			while (scanner.hasNextLine()) {
				records.add(getRecordFromLine(scanner.nextLine()));
			}
		}
		System.out.println("records = " + records);
		List<Bookmark> csvBookmarks = records.stream()
			.map(it -> new Bookmark(BigInteger.valueOf(Long.parseLong(it.get(0))), it.get(1)))
			.toList();

		this.theBookmarks.addAll(csvBookmarks);
	}
	@SneakyThrows
	public void readCsv1() {
		List<List<String>> records = new ArrayList<>();
		String bmUrl = System.getenv("BM_URL");
		URI uri = URI.create(bmUrl);
		var conn = uri.toURL().openConnection();
		try (Scanner scanner = new Scanner(new BufferedInputStream(conn.getInputStream()))) {
			while (scanner.hasNextLine()) {
				records.add(getRecordFromLine(scanner.nextLine()));
			}
		}
		System.out.println("records = " + records);
		List<Bookmark> csvBookmarks = records.stream()
			.map(it -> new Bookmark(BigInteger.valueOf(Long.parseLong(it.get(0))), it.get(1)))
			.toList();

		this.theBookmarks.addAll(csvBookmarks);
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

	private List<Bookmark> bookmarks() {
//
		int i = 0;
		return List.of(
			new Bookmark(BigInteger.valueOf(i++), "https://programmingpercy.tech/blog/opengraph-protocol-how-and-why"),
			new Bookmark(BigInteger.valueOf(i++), "https://youtu.be/c7pgqHNTXQM?si=GnOyPeJzK_tBgp4c"),
			new Bookmark(BigInteger.valueOf(i++), "https://www.youtube.com/watch?v=qsk2JZT_vIc"),
			new Bookmark(BigInteger.valueOf(i++), "https://medium.com/@nuno.mt.sousa/part-ii-creating-a-kafka-cluster-test-extension-569ed750d137"),

//		new Bookmark(BigInteger.valueOf(i++), "https://programmingpercy.tech/blog/opengraph-protocol-how-and-why"),
//		new Bookmark(BigInteger.valueOf(i++), "https://www.heise.de"),
//		new Bookmark(BigInteger.valueOf(i++), "https://programmingpercy.tech/blog/opengraph-protocol-how-and-why"),
//		new Bookmark(BigInteger.valueOf(i++), "https://programmingpercy.tech/blog/opengraph-protocol-how-and-why"),
//		new Bookmark(BigInteger.valueOf(i++), "https://www.heise.de"),
//		new Bookmark(BigInteger.valueOf(i++), "https://programmingpercy.tech/blog/opengraph-protocol-how-and-why"),
//		new Bookmark(BigInteger.valueOf(i++), "https://programmingpercy.tech/blog/opengraph-protocol-how-and-why"),
//		new Bookmark(BigInteger.valueOf(i++), "https://www.heise.de"),
//		new Bookmark(BigInteger.valueOf(i++), "https://programmingpercy.tech/blog/opengraph-protocol-how-and-why"),
//		new Bookmark(BigInteger.valueOf(i++), "https://programmingpercy.tech/blog/opengraph-protocol-how-and-why"),
			new Bookmark(BigInteger.valueOf(i++), "https://www.heise.de")
		);
	}

	public List<Bookmark> getAllBookmarks() {
		return theBookmarks;
	}

	public Bookmark getById(BigInteger id) {
		return theBookmarks.stream().filter(it -> id.equals(it.id())).findFirst().orElse(new Bookmark(BigInteger.ZERO, "https://www.heise.de"));
	}

}
