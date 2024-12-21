@import org.svenehrke.mybookmarks.components.editcard.EditCardTagsComponent
@import org.svenehrke.mybookmarks.components.editcard.EditCardTagsComponent.Ctx
@import org.svenehrke.mybookmarks.components.editcard.NewTagComponent

@param Ctx ctx
<div id="edit-card-tags_${ctx.id()}">
<div class="mb-normal">
    @for(String tag: ctx.tags())
        <bal-tag color="green"
                 hx-trigger="click"
                 hx-put="${EditCardTagsComponent.REMOVE_TAG_URL}"
                 hx-vals='{"id": "${ctx.id()}", "tag": "${tag}"}'
                 hx-target="#edit-card-tags_${ctx.id()}"
                 hx-swap="outerHTML"
        >${tag}</bal-tag>
    @endfor
</div>
<div class="mb-normal">
    @for(String tag: ctx.availableTags())
        <bal-tag color=""
                 hx-trigger="click"
                 hx-put="${EditCardTagsComponent.ADD_TAG_URL}"
                 hx-vals='{"id": "${ctx.id()}", "tag": "${tag}"}'
                 hx-target="#edit-card-tags_${ctx.id()}"
                 hx-swap="outerHTML"
        >
            ${tag}
        </bal-tag>
    @endfor
    <bal-tag id="new-tag-button" color=""
             hx-trigger="click"
             hx-get="${NewTagComponent.uiUrl(ctx.id())}"
             hx-target="#new-tag-button"
             hx-swap="outerHTML"
    >+</bal-tag>
</div>
<div class="mb-normal">
</div>
</div>
