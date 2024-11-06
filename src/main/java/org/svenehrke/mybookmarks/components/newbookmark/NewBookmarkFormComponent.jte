@import org.svenehrke.mybookmarks.components.newbookmark.NewBookmarkFormComponent.Ctx
@import org.svenehrke.mybookmarks.components.newbookmark.CancelNewBookmarkAction
@import org.svenehrke.mybookmarks.components.newbookmark.PostBookmarkAction
@import org.svenehrke.mybookmarks.components.formcontent.FormContentComponent
@import org.svenehrke.mybookmarks.components.image.ImageComponent
@import java.math.BigInteger

@param Ctx ctx

${new ImageComponent.Ctx("https://placehold.co/640x336/png?text=PREVIEW...")}
<form
        hx-post="${PostBookmarkAction.URL}"
        hx-target="closest bal-card-content"
        hx-swap="innerHTML"
>
    ${new FormContentComponent.Ctx(BigInteger.ZERO, "", "", @`
    <bal-button color="text"
                hx-trigger="click"
                hx-get="${CancelNewBookmarkAction.URL}"
                hx-target="closest bal-card-content"
                hx-swap="innerHTML"
    >
        Cancel
    </bal-button>
    ` )}
</form>
