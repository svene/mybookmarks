package org.svenehrke.mybookmarks;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
@Slf4j
public class TriggerController {

	private final BookmarkService bookmarkService;
	private final BookmarkSessionStore bookmarkSessionStore;
	private final FragmentHelper fh;

	@PutMapping("/search/tags")
	@ResponseBody
	public String searchTags(
		@RequestParam(required = false, name = "search_by_tags") String searchByTags,
		HttpServletResponse response
	) {
		bookmarkSessionStore.setSearchTags(searchByTags);
		response.setHeader("HX-Trigger", "searchTagsChanged");
		return "";
	}

	@PostMapping("/reload")
	@ResponseBody
	public String reload(HttpServletResponse response, Model model) {
		bookmarkService.reload();
		response.setHeader("HX-Trigger", "bookmarksChanged");
		return "";
	}

	@PostMapping(path = "/bookmark", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	@ResponseBody
	public String addBookmark(@RequestParam(name = "bm-url") String bmUrl, HttpServletResponse response) {
		bookmarkService.addBookmark(bmUrl);
		response.setHeader("HX-Trigger", "bookmarksChanged, newPreview");
		response.setStatus(HttpStatus.CREATED.value());
		return "";
	}

	@PutMapping(path = "/preview-url", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	@ResponseBody
	public String previewUrl(HttpServletResponse response, @RequestParam("bm-url") String bmUrl) {
		bookmarkService.setPreviewBookmark(bmUrl);
		response.setHeader("HX-Trigger", "newPreview");
		return "";
	}

}
