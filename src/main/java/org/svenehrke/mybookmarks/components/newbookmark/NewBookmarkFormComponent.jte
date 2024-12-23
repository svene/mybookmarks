@import org.svenehrke.mybookmarks.components.addbookmark.AddBookmarkComponent
@import org.svenehrke.mybookmarks.components.editcard.EditCardTagsComponent
@import org.svenehrke.mybookmarks.components.newbookmark.NewBookmarkFormComponent.Ctx
@import org.svenehrke.mybookmarks.components.newbookmark.PostBookmarkAction
@import org.svenehrke.mybookmarks.components.formcontent.FormContentComponent
@import org.svenehrke.mybookmarks.components.image.ImageComponent
@import java.math.BigInteger
@import java.util.List

@param Ctx ctx

${new ImageComponent.Ctx("https://placehold.co/640x336/png?text=PREVIEW...")}
<form
        hx-post="${PostBookmarkAction.URL}"
        hx-target="closest bal-card-content"
        hx-swap="innerHTML"
>
    <bal-form-grid>
    <bal-form-col>
        <div class="field">
            <label class="label">URL</label>
            <div class="control">
                <input class="input"
                       placeholder="URL"
                       name="url"
                       value=""
                       hx-trigger="keyup delay:800ms"
                       hx-get='${FormContentComponent.URL_CHANGED_URL}'
                       hx-swap="none"
                >
            </div>
            <p class="help">URL for the bookmark</p>
        </div>
        ${new EditCardTagsComponent.Ctx(ctx.bookmarkSessionService(), BigInteger.ZERO, List.of())}
    </bal-form-col>

    <bal-form-col>
        <bal-button-group position="right" reverse="true">
            <bal-button color="text"
                        hx-trigger="click"
                        hx-get="${AddBookmarkComponent.URL}"
                        hx-target="closest bal-card-content"
                        hx-swap="innerHTML"
            >
                Cancel
            </bal-button>
            <bal-button element-type="submit" color="primary">
                Submit
            </bal-button>
        </bal-button-group>
    </bal-form-col>

    </bal-form-grid>

</form>
