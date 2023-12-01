@import org.svenehrke.mybookmarks.Bookmark;

@param java.util.List<Bookmark> urls

<div>
	<a href="/preview" target="_blank">Preview</a>
	<a href="/page/csv" target="_blank">CSV</a>
</div>

<div>
@for(Bookmark bm : urls)
	<div class="bm-box">
		<div id="ogimg_${bm.id()}" class="bm-card">
			<div class="bm-card-loading"></div>
		</div>
		<div hx-trigger="revealed"
			 hx-get="/page/${bm.id()}"
			 hx-vals='{"id": "${bm.id()}"}"}'
			 hx-target="#ogimg_${bm.id()}"
			 hx-swap="outerHTML"
		>
		</div>
	</div>
@endfor
</div>
