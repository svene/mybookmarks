@import org.svenehrke.mybookmarks.components.bookmarks.BookmarksComponent.Ctx

@param Ctx ctx

@template.bookmarks.page.layout(
init = "init",
content = @`
	<bal-stack layout="vertical" align="center" class="mb-small">
		<style>
			me bal-card { max-width: 50%; min-width: 50%; }
		</style>

		<bal-card>
			<bal-card-content>
			<bal-card-title level="x-large">Overview</bal-card-title>
			<div id="existing-tags" class="mb-normal">
				${ctx.ME().existingTagsComponent.ctx()}
			</div>
			<div id="csv-text">
				${ctx.ME().csvTextComponent.ctx()}
			</div>
			</bal-card-content>
		</bal-card>

	</bal-stack>

	<bal-divider class="mb-normal"></bal-divider>

	<div id="search-result">
		${ctx.ME().bookmarkRowsComponent.ctx()}
	</div>
`)
