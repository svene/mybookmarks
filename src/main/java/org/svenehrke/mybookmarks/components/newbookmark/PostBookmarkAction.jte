@import org.svenehrke.mybookmarks.components.newbookmark.PostBookmarkAction.Ctx
@import org.svenehrke.mybookmarks.components.existingtags.ExistingTagsComponent
@import org.svenehrke.mybookmarks.components.bookmarkrows.BookmarkRowsComponent

@param Ctx ctx

@template._widget.addbookmark()

<div id="existing-tags" hx-swap-oob="innerHTML">
	${new ExistingTagsComponent.Ctx(ctx.ME().bookmarkSessionService)}
</div>

<div id="search-result" hx-swap-oob="innerHTML">
	${new BookmarkRowsComponent.Ctx(ctx.ME().bookmarkSessionService)}
</div>

