package org.svenehrke.mybookmarks;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@AllArgsConstructor
@Slf4j
public class MainController {

	@GetMapping("/")
	public RedirectView index() {
		return new RedirectView("/bookmarks");
	}

}
