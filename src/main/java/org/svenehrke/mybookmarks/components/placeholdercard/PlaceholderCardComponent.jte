@import org.svenehrke.mybookmarks.components.placeholdercard.PlaceholderCardComponent.Ctx
@import org.svenehrke.mybookmarks.components.card.CardComponent

@param Ctx ctx

<bal-card-content id="ogcard_${ctx.id()}"
                  hx-trigger="revealed"
                  hx-get="${CardComponent.uiUrl(ctx.id())}"
                  hx-target="${CardComponent.cssCardSelector(ctx.id())}"
                  hx-swap="innerHTML"
>
    <img class="mb-normal" src="https://placehold.co/640x336/png?text=LOADING...">
    <bal-card-title level="x-large">${ctx.id()}</bal-card-title>
    <bal-card-subtitle>...</bal-card-subtitle>
    <bal-card-subtitle>...</bal-card-subtitle>
</bal-card-content>
