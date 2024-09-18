@import org.svenehrke.mybookmarks.CardComponent.Ctx

@param Ctx ctx
!{var card = ctx.card(); }

<bal-card-content>
    <img class="mb-normal" src="https://placehold.co/640x336/png?text=LOADING...">
    <div id="card-content-text-${card.id()}">
        <bal-card-title level="x-large">${card.ogTitle()}</bal-card-title>
        <bal-card-subtitle bold="true">${card.host()}</bal-card-subtitle>
        <bal-card-subtitle>${card.ogDescription()}</bal-card-subtitle>

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
<%--
	<div class="card bm-card hoverable tooltipped pb-2" data-position="bottom" data-tooltip="${card.tagString()}">
		<div class="card-image">
			<a href="${card.url()}" target="_blank" class="bm-link">
				<img class="og-image" src="${card.ogImageUrl()}" alt='Open Graph Image'>
			</a>
		</div>
		<div class="card-content">
			<span class="card-title">${card.ogTitle()}</span>
			<div class="bm-host">${card.host()}</div>
			<div class="bm-description">${card.ogDescription()}</div>
			<div class="mt-2 mb-2">
				@for(String tag : card.tags())
					<span class="badge new teal" data-badge-caption="${tag}"></span>
				@endfor
			</div>
		</div>
	</div>
--%>
