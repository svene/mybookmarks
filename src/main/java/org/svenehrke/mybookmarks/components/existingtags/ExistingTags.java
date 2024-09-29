package org.svenehrke.mybookmarks.components.existingtags;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

import java.util.Map;

@RequiredArgsConstructor
@Controller
public class ExistingTags {

	public static final String URL = "/existing_tags";

	private final BookmarkSessionService bookmarkSessionService;


	@GetMapping(URL)
	public ModelAndView existingTags() {
		return new ModelAndView(
			"_widget/existingtags",
			Map.of("tags", bookmarkSessionService.getTags())
		);
	}

}
