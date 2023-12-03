@import org.svenehrke.mybookmarks.Bookmark

@param java.util.List<Bookmark> bookmarks

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
