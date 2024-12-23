@import org.svenehrke.mybookmarks.components.editcard.NewTagComponent
@import org.svenehrke.mybookmarks.components.editcard.NewTagComponent.Ctx

@param Ctx ctx

<div class="mt-normal">
    <form
            hx-post="${NewTagComponent.addTagsUrl(ctx.id())}"
            hx-target="#edit-card-tags_${ctx.id()}"
            hx-swap="outerHTML"
    >
        <bal-field>
            <bal-field-control>
                <bal-input name="newtags" placeholder="enter new tag(s), e.g.: htmx,css,angular"></bal-input>
                <bal-button element-type="submit" color="primary">Add</bal-button>
            </bal-field-control>
        </bal-field>
    </form>
</div>
