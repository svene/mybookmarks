package org.svenehrke.mybookmarks.controller;

import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.svenehrke.mybookmarks.components.bookmarkrows.BookmarkRowsComponent;
import org.svenehrke.mybookmarks.components.bookmarks.BookmarksComponent;
import org.svenehrke.mybookmarks.components.card.CardComponent;
import org.svenehrke.mybookmarks.components.csvtext.CsvTextComponent;
import org.svenehrke.mybookmarks.components.editcard.EditCardComponent;
import org.svenehrke.mybookmarks.components.existingtags.ExistingTags;
import org.svenehrke.mybookmarks.components.image.ImageComponent;
import org.svenehrke.mybookmarks.components.newbookmark.NewBookmarkComponent;

import java.math.BigInteger;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ComponentController {
	private final BookmarksComponent bookmarksComponent;
	private final CardComponent cardComponent;
	private final EditCardComponent editCardComponent;
	private final BookmarkRowsComponent bookmarkRowsComponent;
	private final ExistingTags existingTags;
	private final CsvTextComponent csvTextComponent;
	private final NewBookmarkComponent newBookmarkComponent;
	private final ImageComponent imageComponent;

	@GetMapping("/card/{id}")
	public ViewContext card_id(@PathVariable BigInteger id) {
		return cardComponent.render(id);
	}

	@GetMapping("/redirect/card/{id}")
	public ViewContext redirect_card_id(
		@PathVariable BigInteger id,
		HttpServletResponse response
	) {
		response.setHeader("HX-Trigger", "bookmarksChanged");
		return cardComponent.render(id);
	}

	@GetMapping(EditCardComponent.URL)
	public ViewContext editInlineForm(@RequestParam BigInteger id) {
		return editCardComponent.render(id);
	}

	@GetMapping(NewBookmarkComponent.URL)
	public ViewContext newBookmarkForm() {
		return newBookmarkComponent.render();
	}

	@GetMapping(BookmarkRowsComponent.URL)
	public ViewContext bookmarkRows() {
		return bookmarkRowsComponent.render();
	}

	@GetMapping(ExistingTags.URL)
	public ModelAndView existingTags() {
		return existingTags.modelAndView();
	}

	@GetMapping(CsvTextComponent.URL)
	public ViewContext csvTextField() {
		return csvTextComponent.render();
	}

	@GetMapping("/image")
	public ViewContext image() {
		return imageComponent.render();
	}

	@GetMapping(BookmarksComponent.URL)
	public ViewContext bookmarks() {
		return bookmarksComponent.render();
	}

}
