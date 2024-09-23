package org.svenehrke.mybookmarks;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collections;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class TriggerController {

	private final BookmarkService bookmarkService;
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
		bookmarkSessionService.reload();
		response.setHeader("HX-Trigger", "bookmarksChanged");
		return "";
	}

	@PostMapping(path = "/bookmark", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public RedirectView addBookmark(
		@RequestParam String url,
		HttpServletRequest request,
		HttpServletResponse response
	) {
		request.setAttribute(
			View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.SEE_OTHER); // 303 (See Other) instead of 302 (Found)

		bookmarkSessionService.addBookmark(url);
		response.setHeader("HX-Trigger", "bookmarksChanged, newPreview");
		return new RedirectView("/redirect/card/" + 227);
	}

	@PutMapping(path = "/preview-url", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	@ResponseBody
	public String previewUrl(HttpServletResponse response, @RequestParam("bm-url") String bmUrl) {
		bookmarkSessionService.setPreviewBookmark(bmUrl);
		response.setHeader("HX-Trigger", "newPreview");
		return "";
	}
	@DeleteMapping("/preview")
	@ResponseBody
	public String removePreview(HttpServletResponse response, Model model) {
		bookmarkSessionService.removePreviewBookmark();
		response.setHeader("HX-Trigger", "bookmarksChanged");
		return "";
	}

	@GetMapping("/urlchanged")
	@ResponseBody
	public String urlchanged(@RequestParam String url, HttpServletResponse response) {
		bookmarkSessionService.setPreviewBookmark(url);
		Bookmark previewBookmark = bookmarkSessionStore.getPreviewBookmark();
		bookmarkSessionService.createBookmarkExIfNecessary(previewBookmark);
		response.setHeader("HX-Trigger", "urlChanged");
		return "";
	}

	@GetMapping("/urlchanged0")
	@ResponseBody
	public String urlchanged0(@RequestParam String url, HttpServletResponse response) {
		String value = """
			{"urlChanged": {
				"id": 5,
				"url": "URL"
				}
			}
			"""
			.replace("URL", url);
		response.setHeader("HX-Trigger", value);
		return "";
	}


}
