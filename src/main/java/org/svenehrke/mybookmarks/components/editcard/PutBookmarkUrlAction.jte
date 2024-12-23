@import org.svenehrke.mybookmarks.components.editcard.PutBookmarkUrlAction.Ctx
@import org.svenehrke.mybookmarks.components.card.CardComponent

@param Ctx ctx

${CardComponent.ctx(ctx.bookmarkSessionService(), ctx.id())}

