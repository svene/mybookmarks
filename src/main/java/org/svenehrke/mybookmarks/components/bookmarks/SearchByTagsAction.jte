@import org.svenehrke.mybookmarks.components.bookmarks.SearchByTagsAction.Ctx

@param Ctx ctx

<div id="search-result" hx-swap-oob="outerHTML">
	${ctx.ME().bookmarkRowsComponent.ctx()}
</div>

