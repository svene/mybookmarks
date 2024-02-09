package org.svenehrke.mybookmarks;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Slf4j
public class BookmarkRetriever {

	public Card getCard(String url, BookmarkEx bmx) {
		return CardBuilder.builder()
			.url(url)
			.host(bmx.uri().getHost())
			.ogImageUrl(bmx.imageUrl())
			.ogTitle(bmx.title())
			.ogDescription(bmx.description())
			.build();
	}
	public BookmarkEx buildBookmarkEx(Bookmark bm) {
		URI uri = URI.create(bm.url());
		String html = makeHttpCall(uri, true);
		Document doc = Jsoup.parse(html);

		String ogImageContent = getOpenGraphElementsContent(doc, "og:image", "https://placehold.co/1200x630/png?text=NO PREVIEW");
		String title = getOpenGraphElementsContent(doc, "og:title", null);
		if (title == null) {
			title = getTitle(doc, bm.url());
		}
		BookmarkEx ex = BookmarkExBuilder.builder()
			.uri(uri)
			.imageUrl(newUrlFromPossiblyRelativeUrl(uri, ogImageContent))
			.title(title)
			.description(getOpenGraphElementsContent(doc, "og:description", ""))
			.build();
		return ex;
	}

	private String makeHttpCall(URI uri, boolean followRedirect) {
		List<HttpStatusCode> redirectHttpStatusCodes = Arrays.asList(301, 303).stream().map(it -> HttpStatusCode.valueOf(it)).toList();
		RestClient restClient = RestClient.create();
		log.debug("uri = {0}", uri);
		ResponseEntity<String> result = null;
		try {
			result = restClient
				.get()
				.uri(uri)
				.retrieve()
				.toEntity(String.class);
		} catch (RuntimeException e) {
			log.error("cannot retrieve URL %s".formatted(uri));
			throw e;
		}
		if (result.getStatusCode() != HttpStatusCode.valueOf(200)) {
			throw new RuntimeException("cannot retrieve URL %s. Status code: %s".formatted(uri, result.getStatusCode()));
		}

		if (redirectHttpStatusCodes.contains(result.getStatusCode())) {
			if (followRedirect) {
				var hs = result.getHeaders();
				String location = hs.get("location").get(0);
				location = newUrlFromPossiblyRelativeUrl(uri, location);
				log.debug("location = {0}", location);
				return makeHttpCall(URI.create(location), false);
			} else {
				return "";
			}
		} else {
			return result.getBody();
		}
	}

	private static String newUrlFromPossiblyRelativeUrl(URI baseUri, String url) {
		if (url.startsWith("http")) {
			return url;
		}
		String format = url.startsWith("/") ? "%s%s" : "%s/%s";
		return format.formatted(getDomain(baseUri), url);
	}
	private static String getDomain(URI uri) {
		return "%s://%s".formatted(uri.getScheme(), uri.getHost());
	}
	private static String getTitle(Document doc, String orElse) {
		Element select = doc.select("head title").first();
		return (select != null) ? select.text() : orElse;
	}
	private static String getOpenGraphElementsContent(Document doc, String ogName, String orElse) {
		Element select = doc.select("head meta[property='%s']".formatted(ogName)).first();
		return (select != null) ? select.attributes().get("content") : orElse;
	}

}
