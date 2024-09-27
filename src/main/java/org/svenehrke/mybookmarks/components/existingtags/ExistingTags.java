package org.svenehrke.mybookmarks.components.existingtags;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

import java.util.HashSet;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class ExistingTags {

	public static final String URL = "/existing_tags";

	private final BookmarkSessionService bookmarkSessionService;

	public record Ctx(java.util.List<String> tags) {}

	public Ctx newContext() {
		var tagSet = new HashSet<String>();
		bookmarkSessionService.getBookmarks().forEach(bookmark -> {
			tagSet.addAll(bookmark.tags());

		});
		return new Ctx(tagSet.stream().toList());
	}

	public ModelAndView modelAndView() {
		return new ModelAndView(
			"_widget/existingtags",
			Map.of("ctx", newContext())
		);
	}

	@GetMapping(URL)
	public ModelAndView existingTags() {
		return modelAndView();
	}

}
