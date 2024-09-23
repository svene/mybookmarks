@import org.svenehrke.mybookmarks.components.newbookmark.NewBookmarkComponent.Ctx
@import org.svenehrke.mybookmarks.controller.NewBookmarkController

@param Ctx ctx

${ctx.imageComponentContext()} <%-- Element --%>
<form
        hx-post="${NewBookmarkController.BOOKMARK_URL}"
        hx-target="closest bal-card-content"
        hx-swap="outerHTML"
>
    ${ctx.formContent()} <%-- Element --%>
</form>
