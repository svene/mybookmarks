@import org.svenehrke.mybookmarks.components.placeholdercard.PlaceholderCardComponent.Ctx
@import org.svenehrke.mybookmarks.components.card.CardComponent

@param Ctx ctx

<bal-card-content id="ogcard_${ctx.id()}"
                  hx-trigger="revealed"
                  hx-get="${CardComponent.uiUrl(ctx.id())}"
                  hx-vals='{"id": "${ctx.id()}"}'
                  hx-swap="outerHTML"
>
    <img class="mb-normal" src="https://placehold.co/640x336/png?text=LOADING...">
    <bal-card-title level="x-large">${ctx.id()}</bal-card-title>
    <bal-card-subtitle>...</bal-card-subtitle>
    <bal-card-subtitle>...</bal-card-subtitle>
</bal-card-content>
