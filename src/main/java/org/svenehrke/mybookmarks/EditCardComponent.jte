@import org.svenehrke.mybookmarks.EditCardComponent.Ctx

@param Ctx ctx
!{var card = ctx.card(); }

<form
        hx-put="edit/inline/putbookmark"
        hx-target="closest bal-card-content"
        hx-swap="outerHTML"
>
    ${ctx.formContentComponent()}
</form>
