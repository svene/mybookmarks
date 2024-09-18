package org.svenehrke.mybookmarks;

import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

@Controller
@RequiredArgsConstructor
public class ComponentController {
	private final MessageComponent messageComponent;
	private final CardComponent cardComponent;
	private final EditCardComponent editCardComponent;
	private final BookmarkRowsComponent bookmarkRowsComponent;
	private final ExistingTagsComponent existingTagsComponent;

	@GetMapping("/message")
	ViewContext helloWorld() {
		return messageComponent.render();
	}

	@GetMapping("/card/{id}")
	public ViewContext card_id(@PathVariable BigInteger id) {
		return cardComponent.render(id);
	}

	@GetMapping("/edit/inline/form")
	public ViewContext editInlineForm(@RequestParam BigInteger id) {
		return editCardComponent.render(id);
	}

	@GetMapping("/bookmark_rows")
	public ViewContext bookmarkRows() {
		return bookmarkRowsComponent.render();
	}

	@GetMapping("/existing_tags")
	public ViewContext existingTags() {
		return existingTagsComponent.render();
	}

}
