package org.svenehrke.mybookmarks;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigInteger;

@Controller
@AllArgsConstructor
@Slf4j
public class InlineEditingController {

	private final BookmarkRequestStore bookmarkRequestStore;
	private final FragmentHelper fh;
	private final FragmentsController fragmentsController;


	@GetMapping("/edit/inline/form")
	public String editInlineForm(@RequestParam BigInteger id, Model model) {
		bookmarkRequestStore.setCardModel(FragmentHelper.CardModel.build(fh, id));
		return fragmentsController.fragment("edit-inline-form", model);
	}

	@PutMapping("/edit/inline/putbookmark")
	public RedirectView putBookmark(HttpServletRequest request, @RequestParam BigInteger id) {
		// make the browser redirect with a GET instead of a PUT:
		request.setAttribute(
			View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.SEE_OTHER); // 303 (See Other) instead of 302 (Found)
		return new RedirectView("/card/" + id);
	}
}
