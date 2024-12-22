@import org.svenehrke.mybookmarks.components.editcard.PutBookmarkAction.Ctx
@import org.svenehrke.mybookmarks.components.existingtags.ExistingTagsComponent
@import org.svenehrke.mybookmarks.components.card.CardComponent

@param Ctx ctx

${new CardComponent.Ctx(ctx.bookmarkSessionService(), ctx.id())}

<div id="existing-tags" hx-swap-oob="innerHTML">
    ${new ExistingTagsComponent.Ctx(ctx.bookmarkSessionService())}
</div>

