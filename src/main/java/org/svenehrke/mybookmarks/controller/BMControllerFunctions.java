package org.svenehrke.mybookmarks.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

public class BMControllerFunctions {
	public static RedirectView redirect(String uri, HttpServletRequest request) {

		// make the browser redirect with a GET instead of a PUT/POST/DELETE:
		request.setAttribute(
			View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.SEE_OTHER); // 303 (See Other) instead of 302 (Found)
		return new RedirectView(uri);
	}

}
