@import org.svenehrke.mybookmarks.components.image.ImageComponent

@param ImageComponent.Ctx ctx

<img class="mb-normal"
     src="${ctx.url()}"
     hx-trigger="${ImageComponent.EVENT_URL_CHANGED} from:body"
     hx-get="${ImageComponent.URL}"
     hx-swap="outerHTML"
>
