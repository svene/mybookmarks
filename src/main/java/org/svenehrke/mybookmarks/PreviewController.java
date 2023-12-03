package org.svenehrke.mybookmarks;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.Collections;

@Controller
@AllArgsConstructor
@Slf4j
public class PreviewController {

	private final BookmarkService bookmarkService;

	@GetMapping("/preview-result")
	public String previewResult(HttpServletRequest request, @RequestParam("bm-url") String bmUrl, Model model) {
		var bookmark = new Bookmark(BigInteger.valueOf(1L), bmUrl, Collections.emptyList());
		BookmarkRetriever bookmarkRetriever = new BookmarkRetriever(bookmark);
		model.addAttribute("card", bookmarkRetriever.getCard());
		return "bookmarks/fragment/preview_card";
	}

}
