package org.svenehrke.mybookmarks;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
@Slf4j
public class CardController {
	private final BookmarkService bookmarkService;
	private final BookmarkSessionStore bookmarkSessionStore;
	private final FragmentHelper fh;
	private final FragmentsController fragmentsController;


}
