@import org.svenehrke.mybookmarks.components.postbookmark.PostBookmarkComponent.Ctx

@param Ctx ctx

@template._widget.addbookmark()

<div id="existing-tags" hx-swap-oob="innerHTML">
	@template._widget.existingtags(tags = ctx.existingTags())
</div>

<div id="search-result" hx-swap-oob="outerHTML">
	${ctx.ME().bookmarkRowsComponent.ctx()}
</div>

<div id="csv-text" hx-swap-oob="innerHTML">
	${ctx.ME().csvTextComponent.ctx()}
</div>

