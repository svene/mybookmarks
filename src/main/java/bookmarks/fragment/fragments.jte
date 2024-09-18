@import org.svenehrke.mybookmarks.FragmentHelper

@param FragmentHelper fragmentHelper
@param String fragment


@if("hello".equals(fragment))
	<p>hello</p>
@endif

@if("empty".equals(fragment))
@endif

@if("csv_textfield".equals(fragment))
	!{var M = FragmentHelper.CsvTextfieldModel.build(fragmentHelper); }
	<textarea id="csvString" hidden class="preview-csv">${M.csvString()}</textarea>
@endif <%-- csv_textfield --%>

@if("add-button".equals(fragment))
	<div class="col offset-s8 s1 mb-2 right-align">
		<a class="btn-floating btn-large waves-effect waves-light"
		   hx-trigger="click"
		   hx-get="/fragments?fragment=new-url"
		   hx-target="#add-or-preview"
		>
			<i class="material-icons">add</i>
		</a>
	</div>
@endif
@if("new-url".equals(fragment))
	<div class="col offset-s3 s6 input-field outlined mb-2">
		<input
			id="bm-url"
			name="bm-url"
			type="text"
			placeholder=" "
			hx-put="/preview-url"
			hx-target="#preview"
			hx-trigger="keyup delay:800ms changed"
		>
		<label for="last_name">New URL</label>
		<span class="supporting-text">Insert a URL to preview the new entry</span>
	</div>
	<div id="preview"></div>
@endif
@if("preview_card".equals(fragment))
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
							<%-- ======== Cancel =============== --%>
							<a class="btn-small waves-effect waves-light"
							   hx-delete="/preview"
							   hx-swap="none"
							>Cancel</a>
							<%-- ======== Add =============== --%>
							<a class="btn-small waves-effect waves-light"
							   hx-post="/bookmark"
							   hx-swap="none"
							   hx-include="#bm-url"
							>Add</a>
						</div>

					</div>
				</div>
			</div>
		</div>
	@endif
@endif


