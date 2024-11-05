@import org.svenehrke.mybookmarks.components.main.SearchByTagsAction.Ctx

@param Ctx ctx

<div id="search-result" hx-swap-oob="outerHTML">
	${ctx.ME().bookmarkRowsComponent.ctx()}
</div>

