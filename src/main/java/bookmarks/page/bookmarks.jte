@template.bookmarks.page.layout(
init = "init",
content = @`

	<bal-stack layout="vertical" align="center" class="mb-small">

		<bal-card style="max-width: 50%; min-width: 50%"
				  hx-trigger="init from:body, bookmarksChanged from:body"
				  hx-get="/existing_tags">
		</bal-card>
		<bal-card style="max-width: 50%; min-width: 50%"
				  hx-trigger="init from:body, bookmarksChanged from:body"
				  hx-get="/csv_textfield">
		</bal-card>

		<%-- Add button --%>
		<bal-card style="max-width: 50%; min-width: 50%"
				  hx-trigger="init from:body, bookmarksChanged from:body"
				  hx-get="/addbookmark"
		>
		</bal-card>
	</bal-stack>

	<div id="search-result"
		 hx-trigger="init from:body, bookmarksChanged from:body, searchTagsChanged from:body"
		 hx-get="/bookmark_rows"
	></div>
`)
