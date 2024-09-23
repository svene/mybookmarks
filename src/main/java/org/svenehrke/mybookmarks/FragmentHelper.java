package org.svenehrke.mybookmarks;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class FragmentHelper {

	private final BookmarkService bookmarkService;
	private final BookmarkSessionStore bookmarkSessionStore;

	public record ExistingTagsModel(List<String> tags) {
		public static ExistingTagsModel build(FragmentHelper fragmentHelper) {
			return new ExistingTagsModel(fragmentHelper.bookmarkSessionStore.getTags());
		}
	}

	@Deprecated
	public record PreviewCardModel(Card card) {
		public static PreviewCardModel build(FragmentHelper fragmentHelper) {
			Bookmark bm = fragmentHelper.getBookmarkSessionStore().getPreviewBookmark();
			Card card;
			if (bm == null) {
				card = null;
			} else {
				fragmentHelper.getBookmarkService().createBookmarkExIfNecessary(bm);
				card = MishMash.getCard(bm, fragmentHelper.getBookmarkSessionStore().getBookmarkEx(bm));
			}
			return new PreviewCardModel(card);
		}
	}

	public record CsvTextfieldModel(String csvString) {
		public static CsvTextfieldModel build(FragmentHelper fh) {
			return new CsvTextfieldModel(fh.getBookmarkSessionStore().getBookmarksCSV());
		}
	}
}
