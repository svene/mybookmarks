package org.svenehrke.mybookmarks;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Component
@Data
public class FragmentHelper {

	private final BookmarkService bookmarkService;
	private final BookmarkSessionStore bookmarkSessionStore;

	public record ExistingTagsModel(String tags) {
		public static ExistingTagsModel build() {
			return new ExistingTagsModel("STUB: <existing tags>");
		}
	}

	public record PreviewCardModel(Card card) {
		public static PreviewCardModel build(FragmentHelper fragmentHelper) {
			Bookmark bm = fragmentHelper.getBookmarkSessionStore().getPreviewBookmark();
			Card card;
			if (bm == null) {
				card = null;
			} else {
				fragmentHelper.getBookmarkService().createBookmarkExIfNecessary(bm);
				card = new BookmarkRetriever().getCard(bm.url(), fragmentHelper.getBookmarkSessionStore().getBookmarkEx(bm));
			}
			return new PreviewCardModel(card);
		}
	}

	public record BookmarkRowsModel(List<Bookmark> bookmarks) {
		public static BookmarkRowsModel build(FragmentHelper fh) {
			return new BookmarkRowsModel(
				fh.getBookmarkService().findByTag(fh.getBookmarkSessionStore().getSearchTags())
			);
		}
	}

	public record CardModel(Card card) {
		public static CardModel build(FragmentHelper fh, BigInteger id) {
			var bookmarks = fh.getBookmarkSessionStore().getBookmarks();
			Bookmark bookmark = fh.getBookmarkService().getById(id, bookmarks);

			fh.bookmarkService.createBookmarkExIfNecessary(bookmark);
			Card card = new BookmarkRetriever().getCard(bookmark.url(), fh.getBookmarkSessionStore().getBookmarkEx(bookmark));
			return new CardModel(card);
		}
	}

	public record CsvTextfieldModel(String csvString) {
		public static CsvTextfieldModel build(FragmentHelper fh) {
			return new CsvTextfieldModel(fh.getBookmarkSessionStore().getBookmarksCSV());
		}
	}
}
