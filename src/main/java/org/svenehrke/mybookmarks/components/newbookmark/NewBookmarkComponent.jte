@import org.svenehrke.mybookmarks.components.newbookmark.NewBookmarkComponent.Ctx

@param Ctx ctx

${ctx.imageComponentContext()} <%-- Element --%>
<form
        hx-post="/bookmark"
        hx-target="closest bal-card-content"
        hx-swap="outerHTML"
>
    ${ctx.formContent()} <%-- Element --%>
</form>
