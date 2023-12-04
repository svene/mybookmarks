@import org.svenehrke.mybookmarks.FragmentHelper.CardModel;

@param CardModel cardModel
!{var card = cardModel.card(); }

<div class="w50 bm-box">
	<a href="${card.url()}" target="_blank" class="bm-link">
		<div class="bm-image">
			<img src="${card.ogImageUrl()}" alt='Open Graph Image'>
		</div>
		<div class="bm-text">
			<div class="bm-host">${card.host()}</div>
			<div class="bm-title">${card.ogTitle()}</div>
			<div class="bm-description">${card.ogDescription()}</div>
		</div>
	</a>
</div>
