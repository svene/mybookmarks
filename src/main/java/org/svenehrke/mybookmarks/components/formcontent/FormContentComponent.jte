@import org.svenehrke.mybookmarks.components.formcontent.FormContentComponent.Ctx

@param Ctx ctx
!{var card = ctx.card(); }

<bal-form-grid>
    <bal-form-col>
        <input type="hidden" name="id" value="${card.id()}">
        <div class="field">
            <label class="label">URL</label>
            <div class="control">
                <input class="input"
                       placeholder="URL"
                       name="url"
                       value="${card.url()}"
                       hx-trigger="keyup delay:800ms"
                       hx-get="/urlchanged"
                       hx-swap="none"
                >
            </div>
            <p class="help">URL for the bookmark</p>
        </div>
        <div>
            <bal-field>
                <bal-field-label>Tags</bal-field-label>
                <bal-field-control>
                    <bal-input
                            name="tags"
                            placeholder="Tags"
                            value="${card.tagString()}"
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
            <bal-button color="text">
                Cancel
            </bal-button>
            <bal-button element-type="submit" color="primary">
                Submit
            </bal-button>
        </bal-button-group>
    </bal-form-col>

</bal-form-grid>
