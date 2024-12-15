@import org.svenehrke.mybookmarks.components.editcard.CancelEditBookmarkAction
@import org.svenehrke.mybookmarks.components.editcard.PutBookmarkAction
@import org.svenehrke.mybookmarks.components.editcard.EditCardComponent.Ctx

@param Ctx ctx

<form
        hx-put="${PutBookmarkAction.URL}"
        hx-target="closest bal-card-content"
        hx-swap="outerHTML"
>
        ${ctx.formContentCtx(@`
                <bal-button color="text"
                            hx-trigger="click"
                            hx-get="${CancelEditBookmarkAction.url(ctx.id())}"
                            hx-target="closest bal-card-content"
                            hx-swap="outerHTML"
                >
                        Close
                </bal-button>
        `
        )}
</form>
