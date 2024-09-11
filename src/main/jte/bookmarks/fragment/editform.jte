<bal-form-grid>
    <bal-form-col>
        <div class="field">
            <label class="label">URL</label>
            <div class="control">
                <input class="input" placeholder="URL" name="url">
            </div>
            <p class="help">This username is available</p>
        </div>
        <div>
            <bal-field>
                <bal-field-label>Tags</bal-field-label>
                <bal-field-control>
                    <bal-input name="tags" placeholder="Tags"></bal-input>
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
            <bal-button color="text" _="on click dismiss() on #edit-modal">
                Cancel
            </bal-button>
            <bal-button color="primary" _="
						on click send 'edit_modal_ok'(payload: {name: 'Bart'}) to me
						dismiss() on #edit-modal
						"
            >
                Okay
            </bal-button>
            <bal-button element-type="submit" color="primary">
                Submit
            </bal-button>
        </bal-button-group>
    </bal-form-col>

</bal-form-grid>
