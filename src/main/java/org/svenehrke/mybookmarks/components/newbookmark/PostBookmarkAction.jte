@import org.svenehrke.mybookmarks.components.addbookmark.AddBookmarkComponent
@import org.svenehrke.mybookmarks.components.newbookmark.PostBookmarkAction.Ctx
@import org.svenehrke.mybookmarks.components.existingtags.ExistingTagsComponent
@import org.svenehrke.mybookmarks.components.bookmarkrows.BookmarkRowsComponent

@param Ctx ctx

${new AddBookmarkComponent.Ctx()}

<div id="existing-tags" hx-swap-oob="innerHTML">
	${new ExistingTagsComponent.Ctx(ctx.bookmarkSessionService())}
</div>

<div id="search-result" hx-swap-oob="innerHTML">
	${new BookmarkRowsComponent.Ctx(ctx.bookmarkSessionService())}
</div>

