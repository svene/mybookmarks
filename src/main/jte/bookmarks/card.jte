@import org.svenehrke.mybookmarks.Card;

@param Card card
<a href="${card.url()}" target="_blank" class="bm-link">
<div class="bm-image">
<%--	<a href="${card.url()}" target="_blank">--%>
		<img src="${card.ogImageUrl()}" alt='Open Graph Image' >
<%--	</a>--%>
</div>
<div class="bm-text">
	<div class="bm-host">${card.host()}</div>
	<div class="bm-title">${card.ogTitle()}</div>
	<div class="bm-description">${card.ogDescription()}</div>
</div>
</a>

