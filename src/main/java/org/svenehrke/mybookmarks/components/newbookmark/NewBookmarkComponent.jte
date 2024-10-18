@import org.svenehrke.mybookmarks.components.newbookmark.NewBookmarkComponent.Ctx
@import org.svenehrke.mybookmarks.components.postbookmark.PostBookmarkComponent
@import java.math.BigInteger

@param Ctx ctx

${ctx.ME().imageComponent.ctx("https://placehold.co/640x336/png?text=PREVIEW...")}
<form
        hx-post="${PostBookmarkComponent.URL}"
        hx-target="closest bal-card-content"
        hx-swap="outerHTML"
>
    ${ctx.ME().formContentComponent.ctx(BigInteger.ZERO, "", "")}
</form>
