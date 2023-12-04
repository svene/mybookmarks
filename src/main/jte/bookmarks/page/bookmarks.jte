@import org.svenehrke.mybookmarks.Bookmark

@template.bookmarks.page.layout(
init = "init",
content = @`

	<div hx-trigger="init from:body, bookmarksChanged from:body, searchTagsChanged from:body" hx-get="/fragment?path=new-url"></div>
	<div hx-trigger="newPreview from:body, bookmarksChanged from:body" hx-get="/fragment?path=preview_card"></div>

	<hr class="w50">
	<form class="w50">
		<div class="grid">
			<input
				id="search_by_tags"
				type="search"
				name="search_by_tags"
				placeholder="tag,tag,..."
				hx-put="/search/tags"
				hx-swap="none"
				hx-trigger="keyup delay:800ms changed"
			/>
			<input
				type="search"
				name="search_by_title"
				placeholder="Title"
				hx-get="/food/search"
				hx-target="#search-result"
				hx-trigger="keyup delay:800ms changed"
			/>
		</div>
	</form>

	<div hx-trigger="init from:body, bookmarksChanged from:body" hx-get="/fragment?path=existing_tags">Tags</div>
	<hr class="w50">

	<div id="csvTextfield" hx-trigger="init from:body, bookmarksChanged from:body" hx-get="/csvtextfield"></div>

	<div id="search-result" hx-trigger="init from:body, bookmarksChanged from:body" hx-get="/fragment?path=bookmark_rows"></div>
`)
