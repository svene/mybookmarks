@import org.svenehrke.mybookmarks.NewBookmarkComponent.Ctx

@param Ctx ctx

<form
        hx-post="/bookmark"
        hx-target="closest bal-card-content"
        hx-swap="outerHTML"
>
    ${ctx.formContent()}
</form>
