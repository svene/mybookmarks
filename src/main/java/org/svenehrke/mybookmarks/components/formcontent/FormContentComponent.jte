@import org.svenehrke.mybookmarks.components.editcard.EditCardTagsComponent
@import org.svenehrke.mybookmarks.components.formcontent.FormContentComponent
@import org.svenehrke.mybookmarks.components.formcontent.FormContentComponent.Ctx

@param Ctx ctx

<bal-form-grid>
    <bal-form-col>
        <input type="hidden" name="id" value="${ctx.id()}">
        <div class="field">
            <label class="label">URL</label>
            <div class="control">
                <input class="input"
                       placeholder="URL"
                       name="url"
                       value="${ctx.url()}"
                       hx-trigger="keyup delay:800ms"
                       hx-get='${FormContentComponent.URL_CHANGED_URL}'
                       hx-swap="none"
                >
            </div>
            <p class="help">URL for the bookmark</p>
        </div>
        ${new EditCardTagsComponent.Ctx(ctx.bookmarkSessionService(), ctx.id(), ctx.tags())}
    </bal-form-col>

    <bal-form-col>
        <bal-button-group position="right" reverse="true">
            ${ctx.cancelButtonSlot()}
            <bal-button element-type="submit" color="primary">
                Submit
            </bal-button>
        </bal-button-group>
    </bal-form-col>

</bal-form-grid>
