@import org.svenehrke.mybookmarks.NewBookmarkComponent.Ctx

@param Ctx ctx

<img class="mb-normal" src="https://placehold.co/640x336/png?text=PREVIEW..."
     hx-trigger="urlChanged from:body"
     hx-get="/image"
     hx-swap="outerHTML"
     X_="on urlChanged from body log event"
>
<form
        hx-post="/bookmark"
        hx-target="closest bal-card-content"
        hx-swap="outerHTML"
>
    ${ctx.formContent()}
</form>
