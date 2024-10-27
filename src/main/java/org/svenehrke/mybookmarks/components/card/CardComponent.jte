@import org.svenehrke.mybookmarks.components.card.CardComponent.Ctx
@import org.svenehrke.mybookmarks.components.editcard.EditCardComponent
@import org.svenehrke.mybookmarks.service.BookmarkUtil

@param Ctx ctx

<bal-card-content>
    <img class="mb-normal" src="${ctx.ogInfo().imageUrl()}">
    <div id="card-content-text-${ctx.id()}">
        <bal-card-title level="x-large">${ctx.ogInfo().title()}</bal-card-title>
        <bal-card-subtitle bold="true">${ctx.ogInfo().uri().getHost()}</bal-card-subtitle>
        <bal-card-subtitle>${ctx.ogInfo().description()}</bal-card-subtitle>

        <bal-card-actions position="right">
            @template._widget.tags(tags = BookmarkUtil.tagsStringToList(ctx.tagsString()))
            <bal-button
                    square="true"
                    icon="edit"
                    hx-get="${EditCardComponent.COMPONENT_URL}?id=${ctx.id()}"
                    hx-target="closest #card-content-text-${ctx.id()}"
                    hx-swap="innerHTML"
            ></bal-button>
        </bal-card-actions>
    </div>
</bal-card-content>
