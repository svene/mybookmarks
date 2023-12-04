package org.svenehrke.mybookmarks;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigInteger;

@Controller
@AllArgsConstructor
@Slf4j
public class BookmarksController {

	private final BookmarkService bookmarkService;
	private final BookmarkSessionStore bookmarkSessionStore;
	private final FragmentHelper fh;

	@GetMapping("/")
	public RedirectView index() {
		return new RedirectView("/page/bookmarks");
	}

	@GetMapping("/card/{id}")
	public String page(@PathVariable BigInteger id, Model model) {
		bookmarkService.loadBookmarksIntoSessionIfNecessary();
		model.addAttribute("cardModel", FragmentHelper.CardModel.build(fh, id));
		return "bookmarks/fragment/card";
	}

}
