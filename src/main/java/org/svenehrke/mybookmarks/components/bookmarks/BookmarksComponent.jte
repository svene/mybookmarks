@import org.svenehrke.mybookmarks.components.bookmarks.BookmarksComponent.Ctx
@import org.svenehrke.mybookmarks.controller.TriggerController

@param Ctx ctx

@template.bookmarks.page.layout(
init = "init",
content = @`
	<bal-stack layout="vertical" align="center" class="mb-small">
		<h1 class="title text-xxx-large mb-xx-large">MyBookmarks <bal-icon name="link" size="large" inline="true"></bal-icon></h1>
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

			<bal-card-actions position="right">
				<bal-button onclick="copyCsvToClipboard()" color="light" inverted="true" icon="copy">CSV</bal-button>
				<bal-button
						color="light"
						inverted="true"
						icon="refresh"
						hx-trigger="click"
						hx-post="${TriggerController.RELOAD_URL}"
						hx-swap="none"
				>CSV
				</bal-button>
			</bal-card-actions>
		</bal-card>
	</bal-stack>

	<bal-divider class="mb-normal"></bal-divider>

	<div id="search-result">
		${ctx.ME().bookmarkRowsComponent.ctx()}
	</div>
`)
