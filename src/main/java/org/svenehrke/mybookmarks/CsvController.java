package org.svenehrke.mybookmarks;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
@Slf4j
public class CsvController {
	private final BookmarkService bookmarkService;
	private final BookmarkSessionStore bookmarkSessionStore;

	@GetMapping("/csv")
	@ResponseBody
	@Deprecated
	public String csv(HttpServletRequest request, Model model) {
		String csv = bookmarkSessionStore.getBookmarksCSV();
		return csv;
	}
	@GetMapping("/page/csv")
	@Deprecated
	public String csvPage(HttpServletRequest request, Model model) {
		bookmarkService.loadBookmarksIntoSessionIfNecessary();
		model.addAttribute("csvString", bookmarkSessionStore.getBookmarksCSV());
		model.addAttribute("csvInfo", bookmarkSessionStore.getBookmarksCsvInfo());
		return "bookmarks/csvpage";
	}
	@GetMapping("/csvfile")
	@Deprecated
	public ResponseEntity<byte[]> csv(HttpServletRequest request, HttpServletResponse response) {
		String csv = bookmarkSessionStore.getBookmarksCSV();
		byte[] fileContent = csv.getBytes();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		headers.setContentDispositionFormData("attachment", "output.csv");
		return new ResponseEntity<>(fileContent, headers, org.springframework.http.HttpStatus.OK);
	}


	@GetMapping("/csvtextfield")
	public String csvTextField(HttpServletRequest request, Model model) {
		bookmarkService.loadBookmarksIntoSessionIfNecessary();
		model.addAttribute("bookmarksCsv", bookmarkSessionStore.getBookmarksCSV());
		return "bookmarks/csv_textfield";
	}


}
