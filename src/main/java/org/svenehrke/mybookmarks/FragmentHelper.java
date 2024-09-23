package org.svenehrke.mybookmarks;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class FragmentHelper {

	private final BookmarkService bookmarkService;
	private final BookmarkSessionStore bookmarkSessionStore;
	private final BookmarkSessionService bookmarkSessionService;


}
