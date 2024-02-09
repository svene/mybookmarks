@import org.svenehrke.mybookmarks.FragmentHelper
@import org.svenehrke.mybookmarks.Bookmark

@param FragmentHelper fragmentHelper
!{var bookmarks = FragmentHelper.BookmarkRowsModel.build(fragmentHelper).bookmarks(); }

<div class="bm-rows">
@for(Bookmark bm : bookmarks)
	<div class="row bm-row">
			<div class="col s3"></div>
			<div class="col s6">
				<div id="ogcard_${bm.id()}"
					 hx-trigger="revealed"
					 hx-get="/card/${bm.id()}"
					 hx-vals='{"id": "${bm.id()}"}"}'
					 hx-swap="outerHTML"
				>
					<div class="bm-card-loading"></div>
				</div>
			</div>
			<div class="col s3"></div>
	</div>
@endfor
</div>
