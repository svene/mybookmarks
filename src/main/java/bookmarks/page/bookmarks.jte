@template.bookmarks.page.layout(
init = "init",
content = @`

	<div hx-trigger="load" hx-get="/message"></div>

	<bal-stack layout="vertical" align="center" class="mb-small">

		<bal-card style="max-width: 50%; min-width: 50%"
				  hx-trigger="init from:body, bookmarksChanged from:body"
				  hx-get="/existing_tags">
		</bal-card>
		<bal-card style="max-width: 50%; min-width: 50%"
				  hx-trigger="init from:body, bookmarksChanged from:body"
				  hx-get="/csv_textfield">
		</bal-card>
	</bal-stack>

	<%-- Add button --%>
<%--
	<div class="row"
		 id="add-or-preview"
		 hx-trigger="init from:body, bookmarksChanged from:body"
		 hx-get="/fragments?fragment=add-button"
	></div>
--%>

    <%-- Preview --%>
<%--
	<div class="row">
		<div class="col s12">
			<div hx-trigger="newPreview from:body, bookmarksChanged from:body"
				 hx-get="/fragments?fragment=preview_card"
			></div>
		</div>
	</div>
--%>

	<div id="search-result"
		 hx-trigger="init from:body, bookmarksChanged from:body, searchTagsChanged from:body"
		 hx-get="/bookmark_rows"
	></div>
`)
