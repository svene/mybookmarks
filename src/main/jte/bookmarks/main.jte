@template.layout.page(
init = "init",
content = @`

	<div id="bookmarks-list" hx-trigger="myEvent from:body, init from:body" hx-get="/bookmarks"></div>

`
)

