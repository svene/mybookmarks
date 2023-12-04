@import org.svenehrke.mybookmarks.FragmentHelper
@import org.svenehrke.mybookmarks.Bookmark

@param FragmentHelper fragmentHelper
!{var bookmarks = FragmentHelper.BookmarkRowsModel.build(fragmentHelper).bookmarks(); }

@for(Bookmark bm : bookmarks)
<div id="ogcard_${bm.id()}"
	 class="bm-card"
	 hx-trigger="revealed"
	 hx-get="/page/${bm.id()}"
	 hx-vals='{"id": "${bm.id()}"}"}'
	 hx-swap="outerHTML"
>
	<div class="bm-card-loading"></div>
</div>
@endfor
