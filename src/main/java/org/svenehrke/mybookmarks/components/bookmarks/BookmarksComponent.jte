@import org.svenehrke.mybookmarks.components.bookmarks.BookmarksComponent.Ctx

@param Ctx ctx

@template.bookmarks.page.layout(
init = "init",
content = @`
	<bal-stack layout="vertical" align="center" class="mb-small">
		<style>
			me bal-card { max-width: 50%; min-width: 50%; }
		</style>

		<bal-card id="existing-tags">
			@template._widget.existingtags(ctx.existingTags())
		</bal-card>

		<bal-card id="csv-text">
<%--			@template.org.svenehrke.mybookmarks.components.csvtext.CsvTextComponent(ctx = ctx.csvTextCtx())--%>
			${ctx.ME().csvTextComponent.ctx()}
		</bal-card>

		<%-- Add button --%>
		<bal-card>
			@template._widget.addbookmark()
		</bal-card>
	</bal-stack>

	<div id="search-result">
		@template.org.svenehrke.mybookmarks.components.bookmarkrows.BookmarkRowsComponent(ctx = ctx.bookmarkRowsCtx())
	</div>

`)
