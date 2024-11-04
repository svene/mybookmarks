@import org.svenehrke.mybookmarks.components.bookmarks.BookmarksComponent.Ctx

@param Ctx ctx

@template.bookmarks.page.layout(
content = @`
	<bal-stack layout="vertical" align="center" class="mb-small">
		<bal-heading level="h1" inverted>MyBookmarks <bal-icon name="link" size="large" inline="true" inverted></bal-icon></bal-heading>
		<style>
			me bal-card { max-width: 50%; min-width: 50%; }
		</style>

		<bal-card id="first_card">
			${ctx.ME().searchComponent.ctx()}
		</bal-card>
	</bal-stack>

	<bal-stack layout="vertical" align="center" class="mb-normal">
		<style>
			me bal-card { max-width: 50%; min-width: 50%; }
		</style>
		<bal-card>
			<bal-card-content>
				@template._widget.addbookmark()
			</bal-card-content>
		</bal-card>
	</bal-stack>

	<div id="search-result">
		${ctx.ME().bookmarkRowsComponent.ctx()}
	</div>

`)
