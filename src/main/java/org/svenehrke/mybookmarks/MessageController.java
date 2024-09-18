package org.svenehrke.mybookmarks;

import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MessageController {
	private final MessageComponent messageComponent;

	@GetMapping("/message")
	ViewContext helloWorld() {
		return messageComponent.render();
	}
}
