@import org.svenehrke.mybookmarks.Bookmark;

@param java.util.List<Bookmark> urls

@template.layout.page(
content = @`

	<div>
	@for(Bookmark bm : urls)
		<article>
			<div id="ogimg_${bm.id()}"></div>
			<div hx-trigger="revealed" hx-get="/page" hx-target="#ogimg_${bm.id()}"></div>
		</article>
	@endfor
	</div>

`
)

