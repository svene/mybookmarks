@import org.svenehrke.mybookmarks.FragmentHelper

@param FragmentHelper fragmentHelper
!{var card = FragmentHelper.PreviewCardModel.build(fragmentHelper).card(); }

@if(card != null)
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
<button type="submit" hx-post="/bookmark" hx-swap="none" hx-include="#bm-url">Add</button>
@endif

