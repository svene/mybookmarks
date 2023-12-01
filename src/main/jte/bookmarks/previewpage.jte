@template.layout.page(
init = "init",
content = @`

	<div>
		<input
			id="bm-url"
			name="bm-url"
			hx-get="/preview-result"
			hx-target="#preview-card"
			hx-trigger="keyup delay:600ms changed"
			placeholder="Paste a URL"
		>
	</div>

	<div id="preview-card" class="bm-box"></div>
	<button hx-post="/bookmark" hx-swap="none" hx-include="#bm-url">Add</button>
	<a hx-boost="false" href="/page/csv" >CSV Preview</a>

	<textarea rows="50"
		  cols="150"
		  class="preview-csv"
		  hx-trigger="bookmarkAdded from:body, init from:body"
		  hx-get="/csv"
	></textarea>

`
)

