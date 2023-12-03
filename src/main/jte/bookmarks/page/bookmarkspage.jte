@import org.svenehrke.mybookmarks.Bookmark

@param java.util.List<Bookmark> bookmarks

@template.bookmarks.page.layout(
init = "init",
content = @`

	<div class="w50">
		<input
			id="bm-url"
			name="bm-url"
			hx-get="/preview-result"
			hx-target="#preview-card"
			hx-trigger="keyup delay:800ms changed"
			placeholder="Insert URL"
		>
	</div>
	<div id="preview-card"></div>

	<hr class="w50">
	<form class="w50">
		<div class="grid">
			<input
				type="search"
				name="search_by_tags"
				placeholder="tag,tag,..."
				hx-get="/bookmarks"
				hx-target="#search-result"
				hx-trigger="keyup delay:200ms changed"
			/>
			<input
				type="search"
				name="search_by_title"
				placeholder="Title"
				hx-get="/food/search"
				hx-target="#search-result"
				hx-trigger="keyup delay:200ms changed"
			/>
		</div>
	</form>

	<div id="csvTextfield" hx-trigger="init from:body, bookmarkAdded from:body" hx-get="/csvtextfield"></div>

	<div id="search-result" hx-trigger="init from:body, bookmarkAdded from:body" hx-get="/bookmarks">
		@template.bookmarks.fragment.bookmark_rows(bookmarks = bookmarks)
	</div>
`)
