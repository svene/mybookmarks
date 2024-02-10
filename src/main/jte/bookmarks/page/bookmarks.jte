@template.bookmarks.page.layout(
init = "init",
content = @`

	<div hx-trigger="init from:body, bookmarksChanged from:body" hx-get="/fragment?path=existing_tags">[Tags]</div>

	<div id="csvTextfield" hx-trigger="init from:body, bookmarksChanged from:body" hx-get="/fragment?path=csv_textfield"></div>


	<%-- Add button --%>
	<div class="row"
		 id="add-or-preview"
		 hx-trigger="init from:body, bookmarksChanged from:body"
		 hx-get="/fragment?path=add-button"
	>
	</div>

    <%-- Preview --%>
	<div class="row">
		<div class="col s12">
			<div hx-trigger="newPreview from:body, bookmarksChanged from:body" hx-get="/fragment?path=preview_card"></div>
		</div>
	</div>

	<div id="search-result" hx-trigger="init from:body, bookmarksChanged from:body, searchTagsChanged from:body" hx-get="/fragment?path=bookmark_rows"></div>
`)
