@import org.svenehrke.mybookmarks.components.bookmarks.BookmarksComponent.Ctx
@import org.svenehrke.mybookmarks.actions.reload.ReloadAction

@param Ctx ctx

@template.bookmarks.page.layout(
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
						hx-post="${ReloadAction.URL}"
						hx-swap="none"
				>CSV
				</bal-button>
			</bal-card-actions>
			<bal-card-content>
				<bal-field>
					<bal-field-label>Search</bal-field-label>
					<bal-field-hint subject="Search by Tag">Comma separated List of tags. E.g.: htmx,css,-angular. Use a - prefix for exclusion</bal-field-hint>
					<bal-input id="search-tags"
							   name="search_by_tags"
							   type="search"
							   placeholder="Search tags"
					></bal-input>
				</bal-field>
			</bal-card-content>
			<bal-card-actions position="right">
				<bal-button hx-put="/search/tags" hx-swap="none" hx-include="#search-tags">Search</bal-button>
			</bal-card-actions>
		</bal-card>
	</bal-stack>

	<bal-divider class="mb-normal"></bal-divider>

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
