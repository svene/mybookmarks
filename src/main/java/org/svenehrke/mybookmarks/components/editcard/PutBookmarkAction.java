package org.svenehrke.mybookmarks.components.editcard;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.svenehrke.mybookmarks.model.CsvInfo;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;
import org.svenehrke.mybookmarks.service.CsvReader;

import java.math.BigInteger;
import java.util.ArrayList;

@ViewComponent
@Controller
@AllArgsConstructor
@Slf4j
public class PutBookmarkAction {

	public static final String URL = "/edit/inline/putbookmark";

	public final BookmarkSessionService bookmarkSessionService;

	public record Ctx(PutBookmarkAction ME, BigInteger id) implements ViewContext {
		public Ctx putBookmark(String url, String tags) {
			ME.bookmarkSessionService.handleNewCsvString(
				putEntryIntoCSV(id, url, tags, ME.bookmarkSessionService.store().getBookmarksCSV())
			);
			return this;
		}

		private static String putEntryIntoCSV(BigInteger id, String url, String tags, String csv) {
			CsvInfo csvInfo = new CsvReader().getCsvInfo(csv);
			var records = new ArrayList<>(csvInfo.records());
			records.set(id.intValue(), url + ";" + tags);
			return String.join(System.lineSeparator(), records);
		}
	}

	@PutMapping(URL)
	public Ctx doit(
		@RequestParam BigInteger id,
		@RequestParam String url,
		@RequestParam String tags
	) {
		return new Ctx(this, id).putBookmark(url, tags);
	}

}
