package org.svenehrke.mybookmarks.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.svenehrke.mybookmarks.components.card.CardComponent;
import org.svenehrke.mybookmarks.service.*;

import java.math.BigInteger;

@Controller
@AllArgsConstructor
@Slf4j
public class InlineEditingController {
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
		bookmarkSessionService.putBookmark(id, url, tags);

		response.setHeader("HX-Trigger", "bookmarksChanged");
		return cardComponent.ctx(id);
	}
}
