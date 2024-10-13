@import org.svenehrke.mybookmarks.components.newbookmark.NewBookmarkComponent.Ctx
@import org.svenehrke.mybookmarks.components.postbookmark.PostBookmarkComponent

@param Ctx ctx

${ctx.imageComponentContext()} <%-- Element --%>
<form
        hx-post="${PostBookmarkComponent.URL}"
        hx-target="closest bal-card-content"
        hx-swap="outerHTML"
>
    ${ctx.formContent()} <%-- Element --%>
</form>
