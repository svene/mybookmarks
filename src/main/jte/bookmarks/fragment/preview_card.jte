@import org.svenehrke.mybookmarks.FragmentHelper

@param FragmentHelper fragmentHelper
!{var card = FragmentHelper.PreviewCardModel.build(fragmentHelper).card(); }

@if(card != null)
	<div class="row mb-2">
		<div class="col s3"></div>
		<div class="col s6">
			<div class="card bm-card">
				<div class="card-image">
					<a href="${card.url()}" target="_blank" class="bm-link">
						<img class="og-image" src="${card.ogImageUrl()}" alt='Open Graph Image'>
					</a>
				</div>
				<div class="card-content">
					<span class="card-title">${card.ogTitle()}</span>
					<div class="bm-host">${card.host()}</div>
					<div class="bm-description">${card.ogDescription()}</div>
					<div class="right-align">
						<a class="btn-small waves-effect waves-light">Cancel</a>
						<a class="btn-small waves-effect waves-light">Add</a>
					</div>

				</div>
			</div>
		</div>
	</div>
@endif
