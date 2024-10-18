package org.svenehrke.mybookmarks.components.newbookmark;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.svenehrke.mybookmarks.components.formcontent.FormContentComponent;
import org.svenehrke.mybookmarks.components.image.ImageComponent;

@ViewComponent
@RequiredArgsConstructor
public class NewBookmarkComponent {

	public static final String URL = "/newbookmark/form";

	public final ImageComponent imageComponent;
	public final FormContentComponent formContentComponent;

	public record Ctx(NewBookmarkComponent ME) implements ViewContext {}

	public Ctx ctx() {
		return new Ctx(this);
	}

}
