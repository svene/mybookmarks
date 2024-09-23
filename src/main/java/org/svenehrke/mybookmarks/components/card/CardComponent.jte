@import org.svenehrke.mybookmarks.components.card.CardComponent.Ctx

@param Ctx ctx
!{var card = ctx.card(); }

<bal-card-content>
    <img class="mb-normal" src="https://placehold.co/640x336/png?text=LOADING...">
    <div id="card-content-text-${card.id()}">
        <bal-card-title level="x-large">${card.ogTitle()}</bal-card-title>
        <bal-card-subtitle bold="true">${card.host()}</bal-card-subtitle>
        <bal-card-subtitle>${card.ogDescription()}</bal-card-subtitle>

        <%-- TODO: show tags: card.tags() --%>
        <bal-card-actions position="right">
            <bal-button
                    square="true"
                    icon="edit"
                    hx-get="/edit/inline/form?id=${card.id()}"
                    hx-target="closest #card-content-text-${card.id()}"
                    hx-swap="innerHTML"
            ></bal-button>
        </bal-card-actions>
    </div>
</bal-card-content>
