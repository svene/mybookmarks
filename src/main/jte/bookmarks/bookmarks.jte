@import org.svenehrke.mybookmarks.Bookmark;

@param java.util.List<Bookmark> urls

@template.layout.page(
content = @`

	<div>
	@for(Bookmark bm : urls)
		<div class="bm-box">
			<div id="ogimg_${bm.id()}">
				<div style="height: 200px"></div>
			</div>
			<div hx-trigger="revealed"
				 hx-get="/page/${bm.id()}"
				 hx-vals='{"id": "${bm.id()}"}"}'
				 hx-target="#ogimg_${bm.id()}"></div>
		</div>
	@endfor
	</div>

`
)

