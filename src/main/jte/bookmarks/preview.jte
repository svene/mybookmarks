@template.layout.page(
init = "init",
content = @`

	<div>
		<input
			id="bm-url"
			name="bm-url"
			hx-get="/preview-result"
			hx-target="#preview-card"
			hx-trigger="keyup delay:200ms changed"
		>
	</div>

	<div id="preview-card" class="bm-box"></div>
	<a hx-boost="false" href="/page/csv" >CSV Preview</a>

`
)

