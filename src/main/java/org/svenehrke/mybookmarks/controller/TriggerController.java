package org.svenehrke.mybookmarks.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.svenehrke.mybookmarks.model.Bookmark;
import org.svenehrke.mybookmarks.service.BookmarkService;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;
import org.svenehrke.mybookmarks.service.BookmarkSessionStore;

import java.util.Collections;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class TriggerController {

	private final BookmarkSessionStore bookmarkSessionStore;
	private final BookmarkSessionService bookmarkSessionService;

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

	public static final String SEARCH_TAGLIST_URL = "/search/taglist";
	@PutMapping(SEARCH_TAGLIST_URL)
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

	public static final String RELOAD_URL = "/reload";
	@PostMapping(RELOAD_URL)
	@ResponseBody
	public String reload(HttpServletResponse response) {
		bookmarkSessionService.reload();
		response.setHeader("HX-Trigger", "bookmarksChanged");
		return "";
	}

	public static final String PREVIEW_URL = "/preview-url";
	@PutMapping(path = PREVIEW_URL, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	@ResponseBody
	public String previewUrl(HttpServletResponse response, @RequestParam("bm-url") String bmUrl) {
		bookmarkSessionService.setPreviewBookmark(bmUrl);
		response.setHeader("HX-Trigger", "newPreview");
		return "";
	}

	public static final String PREVIEW = "/preview";
	@DeleteMapping(PREVIEW)
	@ResponseBody
	public String removePreview(HttpServletResponse response, Model model) {
		bookmarkSessionService.removePreviewBookmark();
		response.setHeader("HX-Trigger", "bookmarksChanged");
		return "";
	}

	public static final String URL_CHANGED = "/urlChanged";

	@GetMapping(URL_CHANGED)
	@ResponseBody
	public String urlchanged(@RequestParam String url, HttpServletResponse response) {
		bookmarkSessionService.setPreviewBookmark(url);
		Bookmark previewBookmark = bookmarkSessionStore.getPreviewBookmark();
		bookmarkSessionService.createBookmarkExIfNecessary(previewBookmark);
		response.setHeader("HX-Trigger", "urlChanged");
		return "";
	}

}
