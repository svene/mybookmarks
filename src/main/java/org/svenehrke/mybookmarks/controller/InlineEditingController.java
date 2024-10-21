package org.svenehrke.mybookmarks.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.svenehrke.mybookmarks.components.card.CardComponent;
import org.svenehrke.mybookmarks.model.CsvInfo;
import org.svenehrke.mybookmarks.service.*;

import java.math.BigInteger;
import java.util.ArrayList;

@Controller
@AllArgsConstructor
@Slf4j
public class InlineEditingController {
	private final BookmarkSessionStore bookmarkSessionStore;
	private final BookmarkSessionService bookmarkSessionService;

	private final CardComponent cardComponent;

	public static final String URL = "/edit/inline/putbookmark";

	@PutMapping(URL)
	public CardComponent.Ctx putBookmark(
		HttpServletResponse response,
		@RequestParam BigInteger id,
		@RequestParam String url,
		@RequestParam String tags
	) {
		String csv = bookmarkSessionStore.getBookmarksCSV();
		CsvInfo csvInfo = new CsvReader().getCsvInfo(csv);
		var records = new ArrayList<>(csvInfo.records());
		records.set(id.intValue(), url + ";" + tags);

		csv = String.join(System.lineSeparator(), records);
		bookmarkSessionService.handleNewCsvString(csv);

		response.setHeader("HX-Trigger", "bookmarksChanged");
		return cardComponent.ctx(id);
	}
}
