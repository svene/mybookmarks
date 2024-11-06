@import org.svenehrke.mybookmarks.components.main.SearchByTagsAction.Ctx
@import org.svenehrke.mybookmarks.components.bookmarkrows.BookmarkRowsComponent

@param Ctx ctx

<div id="search-result" hx-swap-oob="outerHTML">
	${new BookmarkRowsComponent.Ctx(ctx.ME().bookmarkSessionStore, ctx.ME().bookmarkSessionService)}
</div>

