@import org.svenehrke.mybookmarks.components.postbookmark.PostBookmarkComponent.Ctx

@param Ctx ctx

@template._widget.addbookmark()

<div id="existing-tags" hx-swap-oob="innerHTML">
	@template._widget.existingtags(tags = ctx.existingTags())
</div>

<div id="search-result" hx-swap-oob="outerHTML">
	@template.org.svenehrke.mybookmarks.components.bookmarkrows.BookmarkRowsComponent(ctx = ctx.bookmarkRowsCtx())
</div>

<div id="csv-text" hx-swap-oob="innerHTML">
<%--
	@template.org.svenehrke.mybookmarks.components.csvtext.CsvTextComponent(ctx = ctx.csvTextCtx())
--%>
	${ctx.ME().csvTextComponent.ctx()}
</div>

