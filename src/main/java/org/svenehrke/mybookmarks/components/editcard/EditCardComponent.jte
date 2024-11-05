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
                            hx-get="/canceleditbookmark/${ctx.id()}"
                            hx-target="closest bal-card-content"
                            hx-swap="outerHTML"
                >
                        Cancel
                </bal-button>
        `
        )}
</form>
