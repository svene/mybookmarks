@import org.svenehrke.mybookmarks.components.bookmarkrows.BookmarkRowsComponent
@import org.svenehrke.mybookmarks.components.bookmarks.BookmarksComponent.Ctx
@import org.svenehrke.mybookmarks.components.existingtags.ExistingTags

@param Ctx ctx

@template.bookmarks.page.layout(
init = "init",
content = @`
	<bal-stack layout="vertical" align="center" class="mb-small">

		<bal-card style="max-width: 50%; min-width: 50%"
				  hx-trigger="bookmarksChanged from:body"
				  hx-get="${ExistingTags.URL}"
		>
			@template._widget.existingtags(ctx.existingTags())
		</bal-card>

		<bal-card style="max-width: 50%; min-width: 50%">
			${ctx.csvText()}
		</bal-card>

		<%-- Add button --%>
		<bal-card style="max-width: 50%; min-width: 50%">
			@template._widget.addbookmark()
		</bal-card>
	</bal-stack>

	<div id="search-result"
		 hx-trigger="init from:body, bookmarksChanged from:body, searchTagsChanged from:body"
		 hx-get="${BookmarkRowsComponent.URL}"
	></div>

`)
