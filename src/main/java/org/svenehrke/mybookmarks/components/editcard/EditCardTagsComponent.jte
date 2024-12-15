@import org.svenehrke.mybookmarks.components.editcard.EditCardTagsComponent
@import org.svenehrke.mybookmarks.components.editcard.EditCardTagsComponent.Ctx

@param Ctx ctx
<div id="edit-card-tags_${ctx.id()}">
<%--<div id="xxx">--%>
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
</div>
</div>
