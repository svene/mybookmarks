@import org.svenehrke.mybookmarks.components.editcard.CancelEditBookmarkAction
@import org.svenehrke.mybookmarks.components.card.CardComponent
@import org.svenehrke.mybookmarks.components.editcard.PutBookmarkAction
@import org.svenehrke.mybookmarks.components.editcard.EditCardComponent.Ctx

@param Ctx ctx

<form
        hx-put="${PutBookmarkAction.URL}"
        hx-target="closest bal-card-content"
        hx-swap="outerHTML"
>
        <%-- cancelButtonSlot: --%>
        ${ctx.formContentCtx(@`
                <bal-button color="text"
                            hx-trigger="click"
                            hx-get="${CardComponent.uiUrl(ctx.id())}"
                            xhx-get="${CancelEditBookmarkAction.uiUrl(ctx.id())}"
                            hx-target="#card-${ctx.id()}"
                            hx-swap="innerHTML"
                >
                        Close
                </bal-button>
        `
        )}
</form>
