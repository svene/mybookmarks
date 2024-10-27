@import org.svenehrke.mybookmarks.actions.searchbytags.SearchByTagsAction.Ctx

@param Ctx ctx

<div id="search-result" hx-swap-oob="outerHTML">
	${ctx.ME().bookmarkRowsComponent.ctx()}
</div>

