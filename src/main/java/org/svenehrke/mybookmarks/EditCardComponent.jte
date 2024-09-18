@import org.svenehrke.mybookmarks.EditCardComponent.Ctx

@param Ctx ctx
!{var card = ctx.cardModel().card(); }

<form
        hx-put="edit/inline/putbookmark"
        hx-target="closest bal-card-content"
        hx-swap="outerHTML"
>
    <bal-form-grid>
        <bal-form-col>
            <input type="hidden" name="id" value="${card.id()}">
            <div class="field">
                <label class="label">URL</label>
                <div class="control">
                    <input class="input" placeholder="URL" name="url" value="${card.url()}">
                </div>
                <p class="help">This username is available</p>
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
</form>
