@import org.svenehrke.mybookmarks.components.main.ReloadAction.Ctx
@import org.svenehrke.mybookmarks.components.existingtags.ExistingTagsComponent
@import org.svenehrke.mybookmarks.components.bookmarkrows.BookmarkRowsComponent

@param Ctx ctx

@template._widget.addbookmark()

<div id="existing-tags" hx-swap-oob="innerHTML">
	${new ExistingTagsComponent.Ctx(ctx.ME().bookmarkSessionService)}
</div>

<div id="search-result" hx-swap-oob="outerHTML">
	${new BookmarkRowsComponent.Ctx(ctx.ME().bookmarkSessionStore, ctx.ME().bookmarkSessionService)}
</div>
