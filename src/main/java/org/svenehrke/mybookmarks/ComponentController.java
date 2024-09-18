package org.svenehrke.mybookmarks;

import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigInteger;

@Controller
@RequiredArgsConstructor
public class ComponentController {
	private final BookmarkSessionStore bookmarkSessionStore;
	private final BookmarkService bookmarkService;

	private final MessageComponent messageComponent;
	private final CardComponent cardComponent;

	@GetMapping("/message")
	ViewContext helloWorld() {
		return messageComponent.render();
	}

	@GetMapping("/card/{id}")
	public ViewContext card_id(@PathVariable BigInteger id) {
		var cardModel = CardComponent.CardModel.build(bookmarkService, bookmarkSessionStore, id);
		return cardComponent.render(cardModel);
	}
}
