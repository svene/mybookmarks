package org.svenehrke.mybookmarks.components.formcontent;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import gg.jte.Content;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.svenehrke.mybookmarks.service.BookmarkSessionService;

import java.math.BigInteger;
import java.util.List;

@ViewComponent
@Controller
@AllArgsConstructor
public class FormContentComponent {
	private final BookmarkSessionService bookmarkSessionService;

	public record Ctx(
		BookmarkSessionService bookmarkSessionService,
		BigInteger id,
		String url,
		List<String> tags,
		Content cancelButtonSlot
	) implements ViewContext {

	}

	public Ctx ctx(BigInteger id, String url, List<String> tags, Content cancelButtonSlot) {
		return new Ctx(bookmarkSessionService, id, url, tags, cancelButtonSlot);
	}

	public Ctx ctx(Ctx ctx) {
		return ctx;
	}

}
