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
                       hx-get='${FormContentComponent.URL_CHANGED}'
                       hx-swap="none"
                >
            </div>
            <p class="help">URL for the bookmark</p>
        </div>
        ${new EditCardTagsComponent.Ctx(ctx.bookmarkSessionService(), ctx.id(), ctx.tags())}
        <div>
            <bal-field>
                <bal-field-label>Tags</bal-field-label>
                <bal-field-control>
                    <bal-input
                            name="tags"
                            placeholder="Tags"
                            value="---"
                    ></bal-input>
                </bal-field-control>
            </bal-field>
        </div>
        <bal-input-group class="mb-normal" name="tags2">
            <bal-tag closable="true">Tag</bal-tag>
            <bal-tag closable="true">Tag</bal-tag>
            <bal-tag closable="true">Tag</bal-tag>
            <bal-input placeholder="Enter text here"></bal-input>
        </bal-input-group>

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
