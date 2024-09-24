@import org.svenehrke.mybookmarks.components.card.CardComponent.Ctx

@param Ctx ctx

<bal-card-content>
    <img class="mb-normal" src="https://placehold.co/640x336/png?text=LOADING...">
    <div id="card-content-text-${ctx.id()}">
        <bal-card-title level="x-large">${ctx.ogInfo().title()}</bal-card-title>
        <bal-card-subtitle bold="true">${ctx.ogInfo().uri().getHost()}</bal-card-subtitle>
        <bal-card-subtitle>${ctx.ogInfo().description()}</bal-card-subtitle>

        <%-- TODO: show tags: card.tags() --%>
        <bal-card-actions position="right">
            <bal-button
                    square="true"
                    icon="edit"
                    hx-get="/edit/inline/form?id=${ctx.id()}"
                    hx-target="closest #card-content-text-${ctx.id()}"
                    hx-swap="innerHTML"
            ></bal-button>
        </bal-card-actions>
    </div>
</bal-card-content>
