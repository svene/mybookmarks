package org.svenehrke.mybookmarks.service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MishMash {

	public static List<String> filterList(
		List<String> items,
		Predicate<String> stringPredicate
	) {
		return items.stream()
			.filter(stringPredicate)
			.collect(Collectors.toList());
	}
}
