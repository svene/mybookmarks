package org.svenehrke.mybookmarks;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.function.Supplier;

@Slf4j
public class InitialDataLoader {
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

}
