package org.svenehrke.mybookmarks.components.editcard;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.svenehrke.mybookmarks.components.card.CardComponent;
import org.svenehrke.mybookmarks.components.csvtext.CsvTextComponent;
import org.svenehrke.mybookmarks.components.existingtags.ExistingTagsComponent;
import org.svenehrke.mybookmarks.model.CsvInfo;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;
import org.svenehrke.mybookmarks.service.BookmarkSessionStore;
import org.svenehrke.mybookmarks.service.CsvReader;

import java.math.BigInteger;
import java.util.ArrayList;

@ViewComponent
@Controller
@AllArgsConstructor
@Slf4j
public class PutBookmarkAction {

	public static final String URL = "/edit/inline/putbookmark";

	private final BookmarkSessionStore bookmarkSessionStore;
	private final BookmarkSessionService bookmarkSessionService;
	public final CardComponent cardComponent;
	public final ExistingTagsComponent existingTagsComponent;
	public final CsvTextComponent csvTextComponent;

	public record Ctx(PutBookmarkAction ME, BigInteger id) implements ViewContext {}

	public Ctx ctx(BigInteger id) {
		return new Ctx(this, id);
	}

	@PutMapping(URL)
	public Ctx putBookmarkEndpoint(
		@RequestParam BigInteger id,
		@RequestParam String url,
		@RequestParam String tags
	) {
		putBookmark(id, url, tags);
		return ctx(id);
	}

	public void putBookmark(BigInteger id, String url, String tags) {
		bookmarkSessionService.handleNewCsvString(
			putEntryIntoCSV(id, url, tags, bookmarkSessionStore.getBookmarksCSV())
		);
	}

	private static String putEntryIntoCSV(BigInteger id, String url, String tags, String csv) {
		CsvInfo csvInfo = new CsvReader().getCsvInfo(csv);
		var records = new ArrayList<>(csvInfo.records());
		records.set(id.intValue(), url + ";" + tags);
		return String.join(System.lineSeparator(), records);
	}

}
