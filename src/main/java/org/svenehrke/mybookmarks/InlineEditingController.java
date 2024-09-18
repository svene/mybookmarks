package org.svenehrke.mybookmarks;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigInteger;

@Controller
@AllArgsConstructor
@Slf4j
public class InlineEditingController {
	private final BookmarkSessionStore bookmarkSessionStore;


	@PutMapping("/edit/inline/putbookmark")
	public RedirectView putBookmark(
		HttpServletRequest request,
		@RequestParam BigInteger id,
		@RequestParam String tags
	) {
		var bookmarks = bookmarkSessionStore.getBookmarks();
		var newBookmarks = bookmarks.stream()
			.map(it -> it.id().equals(id) ?
				it.withTags(BookmarkUtil.tagStringToList(tags))
				: it).toList();
		bookmarkSessionStore.setBookmarks(newBookmarks);

		// make the browser redirect with a GET instead of a PUT:
		request.setAttribute(
			View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.SEE_OTHER); // 303 (See Other) instead of 302 (Found)
		return new RedirectView("/card/" + id);
	}
}
