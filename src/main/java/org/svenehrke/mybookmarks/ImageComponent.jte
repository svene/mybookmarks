@import org.svenehrke.mybookmarks.ImageComponent.Ctx

@param Ctx ctx

<img class="mb-normal"
     src="${ctx.url()}"
     hx-trigger="urlChanged from:body"
     hx-get="/image"
     hx-swap="outerHTML"
>
