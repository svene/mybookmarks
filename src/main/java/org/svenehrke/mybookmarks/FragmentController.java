package org.svenehrke.mybookmarks;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@Slf4j
public class FragmentController {
	private final BookmarkSessionService bookmarkSessionService;
	private final FragmentHelper fragmentHelper;

	@GetMapping("/fragment")
	public String fragment(@RequestParam(name = "path") String path, Model model) {
		bookmarkSessionService.loadBookmarksIntoSessionIfNecessary();
		model.addAttribute("fragmentHelper", fragmentHelper);
		return "bookmarks/fragment/" + path;
	}


}
