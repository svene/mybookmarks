package org.svenehrke.mybookmarks.components.bookmarkrows;

import de.tschuehly.spring.viewcomponent.core.component.ViewComponent;
import de.tschuehly.spring.viewcomponent.jte.ViewContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.svenehrke.mybookmarks.components.placeholdercard.PlaceholderCardComponent;
import org.svenehrke.mybookmarks.service.*;
import org.svenehrke.mybookmarks.model.Bookmark;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ViewComponent
@RequiredArgsConstructor
@Controller
public class BookmarkRowsComponent {

	private final BookmarkSessionStore bookmarkSessionStore;
	private final BookmarkSessionService bookmarkSessionService;
	public final PlaceholderCardComponent placeholderCardComponent;

	public record Ctx(BookmarkRowsComponent ME) implements ViewContext {}

	public Ctx ctx() {
		return new Ctx(this);
	}

	public List<Bookmark> buildBookmarks() {
		return findAllByTag(bookmarkSessionStore.getSearchTags());
	}
	private List<Bookmark> findAllByTag(String tagsString) {
		if (!StringUtils.hasLength(tagsString)) {
			return bookmarkSessionService.getCsvParseResult().bookmarks();
		}

		var tags = parseTagsString(tagsString);
		// Check that it.tags() does not contain any item from minusTags
		return bookmarkSessionService.getCsvParseResult().bookmarks().stream()
			.filter(it -> tags.normalTags().isEmpty() || !Collections.disjoint(it.tags(), tags.normalTags()))
			.filter(it -> tags.minusTags().isEmpty() || it.tags().stream().noneMatch(tags.minusTags()::contains)) // Check that it.tags() does not contain any item from minusTags
			.collect(Collectors.toList());

	}

	private TagsStringParseResult parseTagsString(String tagsString) {
		var tags = BookmarkUtil.tagsStringToList(tagsString).stream().map(String::trim).toList();
		List<String> plusTags = MishMash.filterList(tags, it -> it.startsWith("+")).stream().map(it -> it.substring(1)).toList();
		List<String> minusTags = MishMash.filterList(tags, it -> it.startsWith("-")).stream().map(it -> it.substring(1)).toList();
		List<String> normalTags = MishMash.filterList(tags, s -> !s.startsWith("+") && !s.startsWith("-"));

		return new TagsStringParseResult(tags, plusTags, minusTags, normalTags);
	}

	private record TagsStringParseResult(
		List<String> tags,
		List<String> plusTags,
		List<String> minusTags,
		List<String> normalTags
	) {}


}
