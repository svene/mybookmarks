@import org.svenehrke.mybookmarks.components.editcard.CancelEditBookmarkAction.Ctx
@import org.svenehrke.mybookmarks.components.card.CardComponent

@param Ctx ctx

${new CardComponent.Ctx(ctx.bookmarkSessionService(), ctx.id())}
