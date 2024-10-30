@import org.svenehrke.mybookmarks.components.editcard.PutBookmarkAction.Ctx
@param Ctx ctx

${ctx.ME().cardComponent.ctx(ctx.id())}

<div id="existing-tags" hx-swap-oob="innerHTML">
    ${ctx.ME().existingTagsComponent.ctx()}
</div>

<div id="csv-text" hx-swap-oob="innerHTML">
    ${ctx.ME().csvTextComponent.ctx()}
</div>
