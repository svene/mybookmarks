package org.svenehrke.mybookmarks.htmx;

public enum HxSwapValues {
	INNER_HTML("innerHTML"),
	OUTER_HTML("outerHTML"),
	TEXT_CONTENT("textContent"),
	BEFORE_BEGIN("beforebegin"),
	AFTER_BEGIN("afterbegin"),
	BEFORE_END("beforeend"),
	AFTER_END("afterend"),
	DELETE("delete"),
	NONE("none")
	;

	private final String value;

	HxSwapValues(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}
}
