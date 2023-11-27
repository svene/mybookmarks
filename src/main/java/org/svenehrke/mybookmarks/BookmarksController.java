package org.svenehrke.mybookmarks;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class BookmarksController {

	private final BookmarkService bookmarkService;
	private final BookmarksWebHelper bookmarksWebHelper;

	@GetMapping("/main")
	public String main(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(true);
		session.setAttribute(BookmarksWebHelper.KEY_BOOKMARKS_CSV, bookmarkService.readCsvAsString());
		return "bookmarks/main";
	}
	@GetMapping("/bookmarks")
	public String bookmarks(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		String csv = bookmarksWebHelper.getCsvStringFromSession(session);
		List<Bookmark> bookmarks = bookmarkService.readCsv(csv);
		session.setAttribute(BookmarksWebHelper.KEY_BOOKMARKS, bookmarks);
		model.addAttribute("urls", bookmarks);

		return "bookmarks/bookmarks";
	}

	@GetMapping("/page/{id}")
	public String page(HttpServletRequest request, @PathVariable BigInteger id, Model model) {
		HttpSession session = request.getSession(false);
		var bookmarks = bookmarksWebHelper.getBookmarksFromSession(session);
		Bookmark bookmark = bookmarkService.getById(id, bookmarks);

		BookmarkRetriever bookmarkRetriever = new BookmarkRetriever(bookmark);
		model.addAttribute("card", bookmarkRetriever.getCard());
		return "bookmarks/card";
	}

	@PostMapping("/bookmarks")
	public String reload(HttpServletRequest request, Model model) {
		bookmarkService.reload();
		return "bookmarks/bookmarks";
	}

	@GetMapping("/csv")
	@ResponseBody
	public String csv(HttpServletRequest request, Model model) {
		return bookmarkService.getCsvAsString();
	}

	@GetMapping("/page/csv")
	public String csvPage(HttpServletRequest request, Model model) {
		model.addAttribute("csvString", bookmarkService.getCsvAsString());
		return "bookmarks/csvpage";
	}





}
