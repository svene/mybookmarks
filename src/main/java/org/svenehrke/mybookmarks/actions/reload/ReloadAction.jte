@import org.svenehrke.mybookmarks.actions.reload.ReloadAction.Ctx

@param Ctx ctx

@template._widget.addbookmark()

<div id="existing-tags" hx-swap-oob="innerHTML">
	${ctx.ME().existingTagsComponent.ctx()}
</div>

<div id="search-result" hx-swap-oob="outerHTML">
	${ctx.ME().bookmarkRowsComponent.ctx()}
</div>

<div id="csv-text" hx-swap-oob="innerHTML">
	${ctx.ME().csvTextComponent.ctx()}
</div>

