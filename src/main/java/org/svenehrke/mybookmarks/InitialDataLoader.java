package org.svenehrke.mybookmarks;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

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
			case "local" -> this::getCsvInputStreamFromLocalFile;
			default -> this::getCsvInputStreamFromURL;
		};
		return new String(inputStreamSupplier.get().readAllBytes());
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
