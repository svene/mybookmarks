@import org.svenehrke.mybookmarks.components.postbookmark.PostBookmarkComponent.Ctx

@param Ctx ctx

@template._widget.addbookmark()

<div id="existing-tags" hx-swap-oob="innerHTML">
	@template._widget.existingtags(tags = ctx.existingTags())
</div>

