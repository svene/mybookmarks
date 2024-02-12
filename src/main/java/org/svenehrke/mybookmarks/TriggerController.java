package org.svenehrke.mybookmarks;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class TriggerController {

	private final BookmarkService bookmarkService;
	private final BookmarkSessionStore bookmarkSessionStore;
	private final FragmentHelper fh;

/*
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

*/
	@PutMapping("/search/taglist")
	@ResponseBody
	public String search_taglist(
		@RequestParam(required = false, name = "tag") List<String> tags,
		HttpServletResponse response
	) {
		var list = (tags == null || tags.isEmpty()) ? Collections.<String>emptyList() : tags;
		bookmarkSessionStore.setSearchTags(String.join(",", list));
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
	@DeleteMapping("/preview")
	@ResponseBody
	public String removePreview(HttpServletResponse response, Model model) {
		bookmarkService.removePreviewBookmark();
		response.setHeader("HX-Trigger", "bookmarksChanged");
		return "";
	}


}
