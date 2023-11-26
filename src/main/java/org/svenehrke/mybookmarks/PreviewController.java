package org.svenehrke.mybookmarks;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class PreviewController {

	public static final String KEY_BOOKMARKS_CSV = "bookmarks-csv";
	public static final String KEY_BOOKMARKS = "bookmarks";
	private final BookmarkService bookmarkService;

	@GetMapping("/preview")
	public String main(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(true);
		session.setAttribute(KEY_BOOKMARKS_CSV, bookmarkService.readCsvAsString());
		return "bookmarks/preview";
	}

	@GetMapping("/preview-result")
	public String previewResult(HttpServletRequest request, @RequestParam("bm-url") String bmUrl, Model model) {
		var bookmark = new Bookmark(BigInteger.valueOf(1L), bmUrl, Collections.emptyList());
		BookmarkRetriever bookmarkRetriever = new BookmarkRetriever(bookmark);
		model.addAttribute("card", bookmarkRetriever.getCard());
		return "bookmarks/card";
	}

}
