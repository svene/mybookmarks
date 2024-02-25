package org.svenehrke.mybookmarks;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
public class InitialDataLoader {

	@SneakyThrows
	public String readCsvAsString() {
		String bmUrl = System.getenv("BM_URL");
		if (bmUrl.contains("file:")) { // e.g: 'file:development/localonly/bookmarks.csv'
			return new String(new FileInputStream(bmUrl.substring("file:".length())).readAllBytes());
		} else {
			return new String(getCsvInputStreamFromURL().readAllBytes());
		}
	}

	@SneakyThrows
	public InputStream getCsvInputStreamFromURL() {
		String bmUrl = System.getenv("BM_URL");
		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(bmUrl))
			.GET()
			.build();
		return HttpClient.newHttpClient().send(
			request, HttpResponse.BodyHandlers.ofInputStream()).body();
	}

}
