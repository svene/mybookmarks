@import org.svenehrke.mybookmarks.actions.putbookmark.PutBookmarkAction.Ctx
@param Ctx ctx

${ctx.ME().cardComponent.ctx(ctx.id())}

<div id="existing-tags" hx-swap-oob="innerHTML">
    ${ctx.ME().existingTagsComponent.ctx()}
</div>
