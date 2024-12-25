package org.svenehrke.mybookmarks.htmx;

import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface HtmxResponseUtils {

	static void setHxTrigger(HttpServletResponse response, String event) {
		response.setHeader("HX-Trigger", event);
	}
	static void setHxTrigger(HttpServletResponse response, List<String> events) {
		response.setHeader("HX-Trigger", String.join(",", events));
	}

	static void setHxReSwap(HttpServletResponse response, HxSwapValues swap) {
		response.setHeader("HX-Reswap", swap.getValue());
	}
}
