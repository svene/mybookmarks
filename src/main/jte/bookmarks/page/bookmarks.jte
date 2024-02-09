@template.bookmarks.page.layout(
init = "init",
content = @`

	<div hx-trigger="init from:body, bookmarksChanged from:body" hx-get="/fragment?path=existing_tags">[Tags]</div>
	<hr class="w50">

	<div id="csvTextfield" hx-trigger="init from:body, bookmarksChanged from:body" hx-get="/fragment?path=csv_textfield"></div>


	<div class="row">
		<div class="col s1 offset-s8 mb-2">
			<a class="btn-floating btn-large waves-effect waves-light">
				<i class="material-icons">add</i>
			</a>
		</div>
	</div>

    <%-- New URL --%>
	<div class="row">
		<div class="col s12">
			<div hx-trigger="init from:body, bookmarksChanged from:body" hx-get="/fragment?path=new-url"></div>
		</div>
	</div>

    <%-- Preview --%>
	<div class="row">
		<div class="col s12">
			<div hx-trigger="newPreview from:body, bookmarksChanged from:body" hx-get="/fragment?path=preview_card"></div>
		</div>
	</div>

	<div id="search-result" hx-trigger="init from:body, bookmarksChanged from:body, searchTagsChanged from:body" hx-get="/fragment?path=bookmark_rows"></div>
`)
