package org.svenehrke.mybookmarks.components.editcard;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.svenehrke.mybookmarks.components.card.CardComponent;

import java.math.BigInteger;

@ViewComponent
@RequiredArgsConstructor
@Controller
public class CancelEditBookmarkAction {
	public final CardComponent cardComponent;
	public record Ctx(CancelEditBookmarkAction ME, BigInteger id) implements ViewContext {}

	@GetMapping("/canceleditbookmark/{id}")
	public Ctx doit(@PathVariable BigInteger id) {
		return new Ctx(this, id);
	}

}
