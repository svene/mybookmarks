@import org.svenehrke.mybookmarks.components.card.CardComponent.Ctx
@import org.svenehrke.mybookmarks.components.editcard.EditCardComponent

@param Ctx ctx

!{var bm = ctx.getBookmark();}
!{var ogInfo = ctx.getOgInfo();}

<bal-card-content>
    <img class="mb-normal" src="${ogInfo.imageUrl()}">
    <div id="card-content-text-${ctx.id()}">
        <bal-card-title level="x-large">${ogInfo.title()}</bal-card-title>
        <bal-card-subtitle bold="true">${ogInfo.uri().getHost()}</bal-card-subtitle>
        <bal-card-subtitle>${ogInfo.description()}</bal-card-subtitle>

        <bal-card-actions position="right">
            @template._widget.tags(tags = bm.tags())
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
