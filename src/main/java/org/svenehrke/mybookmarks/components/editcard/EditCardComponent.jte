@import org.svenehrke.mybookmarks.components.editcard.EditCardComponent.Ctx
@import org.svenehrke.mybookmarks.controller.InlineEditingController

@param Ctx ctx

<form
        hx-put="${InlineEditingController.URL}"
        hx-target="closest bal-card-content"
        hx-swap="outerHTML"
>
        ${ctx.ME().formContentCtx(ctx.id())}
</form>
