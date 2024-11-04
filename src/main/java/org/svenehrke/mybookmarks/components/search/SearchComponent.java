package org.svenehrke.mybookmarks.components.search;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.svenehrke.mybookmarks.components.existingtags.ExistingTagsComponent;

@ViewComponent
@Controller
@RequiredArgsConstructor
public class SearchComponent {

	public static final String UI_URL = "/search/ui";

	public final ExistingTagsComponent existingTagsComponent;

	public record Ctx(SearchComponent ME) implements ViewContext {}

	public Ctx ctx() {
		return new Ctx(this);
	}

	@GetMapping(UI_URL)
	public Ctx ui() {
		return ctx();
	}

}
