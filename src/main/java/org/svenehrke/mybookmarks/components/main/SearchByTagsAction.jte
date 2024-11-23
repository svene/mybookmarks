@import org.svenehrke.mybookmarks.components.main.SearchByTagsAction.Ctx
@import org.svenehrke.mybookmarks.components.bookmarkrows.BookmarkRowsComponent
@import org.svenehrke.mybookmarks.components.existingtags.ExistingTagsComponent

@param Ctx ctx

<div id="existing-tags" hx-swap-oob="innerHTML">
	${new ExistingTagsComponent.Ctx(ctx.bookmarkSessionService())}
</div>

<div id="search-result" hx-swap-oob="outerHTML">
	${new BookmarkRowsComponent.Ctx(ctx.bookmarkSessionService())}
</div>

