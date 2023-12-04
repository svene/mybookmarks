package org.svenehrke.mybookmarks;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
@Slf4j
public class PageController {

	@GetMapping("/pagex/{page}")
	public String page(@PathVariable String page) {
		return "bookmarks/page/" + page;
	}


}
