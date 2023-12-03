package org.svenehrke.mybookmarks;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
@Slf4j
public class CsvController {
	private final BookmarkService bookmarkService;
	private final BookmarkSessionStore bookmarkSessionStore;


	@GetMapping("/csvtextfield")
	public String csvTextField(HttpServletRequest request, Model model) {
		bookmarkService.loadBookmarksIntoSessionIfNecessary();
		model.addAttribute("bookmarksCsv", bookmarkSessionStore.getBookmarksCSV());
		return "bookmarks/fragment/csv_textfield";
	}


}
