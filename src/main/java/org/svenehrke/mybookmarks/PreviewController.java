package org.svenehrke.mybookmarks;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class PreviewController {
	private final BookmarkSessionStore bookmarkSessionStore;

	@PutMapping(path = "/preview-url", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	@ResponseBody
	public String previewUrl(HttpServletResponse response, @RequestParam("bm-url") String bmUrl) {
		var previewBookmark = new Bookmark(BigInteger.valueOf(1L), bmUrl, List.of("todo"));
		bookmarkSessionStore.setPreviewBookmark(previewBookmark);
		response.setHeader("HX-Trigger", "newPreview");
		return "";
	}

	@GetMapping("/preview-result")
	public String previewResult(Model model) {
		Bookmark bm = bookmarkSessionStore.getPreviewBookmark();
		var card = bm == null ? null : new BookmarkRetriever(bm).getCard();
		model.addAttribute("card", card);
		return "bookmarks/fragment/preview_card";
	}

}
