@import org.svenehrke.mybookmarks.components.newbookmark.PostBookmarkAction.Ctx

@param Ctx ctx

@template._widget.addbookmark()

<div id="existing-tags" hx-swap-oob="innerHTML">
	${ctx.ME().existingTagsComponent.ctx()}
</div>

<div id="search-result" hx-swap-oob="innerHTML">
	${ctx.ME().bookmarkRowsComponent.ctx()}
</div>

<div id="csv-text" hx-swap-oob="innerHTML">
	${ctx.ME().csvTextComponent.ctx()}
</div>

