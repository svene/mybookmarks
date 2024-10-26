@import org.svenehrke.mybookmarks.actions.putbookmark.PutBookmarkAction
@import org.svenehrke.mybookmarks.components.editcard.EditCardComponent.Ctx

@param Ctx ctx

<form
        hx-put="${PutBookmarkAction.URL}"
        hx-target="closest bal-card-content"
        hx-swap="outerHTML"
>
        ${ctx.ME().formContentCtx(ctx.id())}
</form>
