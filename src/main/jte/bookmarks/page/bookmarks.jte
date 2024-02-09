@template.bookmarks.page.layout(
init = "init",
content = @`

	<div hx-trigger="init from:body, bookmarksChanged from:body" hx-get="/fragment?path=existing_tags">[Tags]</div>
	<hr class="w50">

	<div id="csvTextfield" hx-trigger="init from:body, bookmarksChanged from:body" hx-get="/fragment?path=csv_textfield"></div>


	<%-- New URL --%>
	<div class="row" id="add-or-preview">
		<div class="col offset-s8 s1 mb-2">
			<a class="btn-floating btn-large waves-effect waves-light"
			   hx-trigger="click"
			   hx-get="/fragment?path=new-url"
			   hx-target="#add-or-preview"
			>
				<i class="material-icons">add</i>
			</a>
		</div>
	</div>

    <%-- New URL --%>
<%--
	<div class="row">
		<div class="col offset-s3 s6">
			<div hx-trigger="init from:body, bookmarksChanged from:body" hx-get="/fragment?path=new-url"></div>
		</div>
	</div>
--%>

    <%-- Preview --%>
	<div class="row">
		<div class="col s12">
			<div hx-trigger="newPreview from:body, bookmarksChanged from:body" hx-get="/fragment?path=preview_card"></div>
		</div>
	</div>

	<div id="search-result" hx-trigger="init from:body, bookmarksChanged from:body, searchTagsChanged from:body" hx-get="/fragment?path=bookmark_rows"></div>
`)
