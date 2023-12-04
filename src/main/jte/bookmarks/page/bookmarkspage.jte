@import org.svenehrke.mybookmarks.Bookmark

@param java.util.List<Bookmark> bookmarks

@template.bookmarks.page.layout(
init = "init",
content = @`

	<div hx-trigger="init from:body, bookmarksChanged from:body" hx-get="/search"></div>
	<div hx-trigger="newPreview from:body, bookmarksChanged from:body" hx-get="/preview-result"></div>

	<hr class="w50">
	<form class="w50">
		<div class="grid">
			<input
				id="search_by_tags"
				type="search"
				name="search_by_tags"
				placeholder="tag,tag,..."
				hx-get="/bookmarks"
				hx-target="#search-result"
				hx-trigger="keyup delay:800ms changed"
				data-tooltip="sweng,news"
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

	<small>Tags</small>
	<hr class="w50">

	<div id="csvTextfield" hx-trigger="init from:body, bookmarksChanged from:body" hx-get="/csvtextfield"></div>

	<div id="search-result" hx-trigger="init from:body, bookmarksChanged from:body" hx-get="/bookmarks">
		@template.bookmarks.fragment.bookmark_rows(bookmarks = bookmarks)
	</div>
`)
