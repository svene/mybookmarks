package org.svenehrke.mybookmarks.components.newbookmark;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.svenehrke.mybookmarks.components.formcontent.FormContentComponent;
import org.svenehrke.mybookmarks.components.image.ImageComponent;

@ViewComponent
@RequiredArgsConstructor
@Controller
public class NewBookmarkFormComponent {

	public static final String URL = "/newbookmark/form";

	public final ImageComponent imageComponent;
	public final FormContentComponent formContentComponent;

	public record Ctx(NewBookmarkFormComponent ME) implements ViewContext {}

	@GetMapping(URL)
	public ViewContext ui() {
		return new Ctx(this);
	}

}
