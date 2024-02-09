@import org.svenehrke.mybookmarks.FragmentHelper.CardModel;

@param CardModel cardModel
!{var card = cardModel.card(); }

<div class="card bm-card hoverable tooltipped" data-position="bottom" data-tooltip="${card.tagString()}">
	<div class="card-image">
		<a href="${card.url()}" target="_blank" class="bm-link">
		<img class="og-image" src="${card.ogImageUrl()}" alt='Open Graph Image'>
		</a>
	</div>
	<div class="card-content">
		<span class="card-title">${card.ogTitle()}</span>
		<div class="bm-host">${card.host()}</div>
		<div class="bm-description">${card.ogDescription()}</div>
	</div>
</div>
