@import org.svenehrke.mybookmarks.Bookmark
@import org.svenehrke.mybookmarks.FragmentHelper

@param FragmentHelper fragmentHelper
@param String fragment


@if("csv_textfield".equals(fragment))
	!{var M = FragmentHelper.CsvTextfieldModel.build(fragmentHelper); }
	<textarea id="csvString" hidden class="preview-csv">${M.csvString()}</textarea>
@endif <%-- csv_textfield --%>

@if("existing_tags".equals(fragment))
	!{var M = FragmentHelper.ExistingTagsModel.build(fragmentHelper); }
	<div class="row mt-4 mb-4" hx-on:htmx:load="window.mbm.initSelect('#tag-select')">
		<div class="input-field col s6 offset-s3">
			<select id="tag-select" name="tag" multiple hx-put="/search/taglist" hx-swap="none">
				@for(String tag : M.tags())
					<option value="${tag}">${tag}</option>
					<li>${tag}</li>
				@endfor
			</select>
			<label for="form-select-2">Select Tags</label>
		</div>
	</div>
@endif <%-- existing_tags --%>
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
@if("bookmark_rows".equals(fragment))
	!{var bookmarks = FragmentHelper.BookmarkRowsModel.build(fragmentHelper).bookmarks(); }

	<bal-modal id="edit-modal" _="
			on do_open_edit_modal open() me
			on balModalWillDismiss log event
			on 'edit_modal_ok' log event.detail.payload
			"
	>
		<bal-modal-header>Modal Title</bal-modal-header>
		<bal-modal-body>
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing?</p>
			<bal-form native="true">
				<bal-form-grid>
					<bal-form-col>
						<div class="field">
							<label class="label">URL</label>
							<div class="control">
								<input class="input" placeholder="URL" type="tel">
							</div>
							<p class="help">This username is available</p>
						</div>
						<div>
							<bal-field>
								<bal-field-label>Tags</bal-field-label>
								<bal-field-control>
									<bal-input placeholder="Tags"></bal-input>
								</bal-field-control>
							</bal-field>
						</div>
						<bal-input-group class="mb-normal">
							<bal-tag closable="true">Tag</bal-tag>
							<bal-tag closable="true">Tag</bal-tag>
							<bal-tag closable="true">Tag</bal-tag>
							<bal-input placeholder="Enter text here"></bal-input>
							<bal-icon name="date"></bal-icon>
						</bal-input-group>

					</bal-form-col>

					<bal-form-col>
						<bal-button-group position="right" reverse="true">
							<bal-button color="text" _="on click dismiss() on #edit-modal">
								Cancel
							</bal-button>
							<bal-button color="primary" _="
						on click send 'edit_modal_ok'(payload: {name: 'Bart'}) to me
						dismiss() on #edit-modal
						"
							>
								Okay
							</bal-button>
						</bal-button-group>
					</bal-form-col>

				</bal-form-grid>
			</bal-form>

		</bal-modal-body>
	</bal-modal>

	<bal-stack layout="vertical" align="center">
		@for(Bookmark bm : bookmarks)
			<bal-card style="max-width: 50%; min-width: 50%">
				<bal-card-content id="ogcard_${bm.id()}"
								  hx-trigger="revealed"
								  hx-get="/card/${bm.id()}"
								  hx-vals='{"id": "${bm.id()}"}"}'
								  hx-swap="outerHTML"
				>
					<img class="mb-normal" src="https://placehold.co/640x336/png?text=LOADING...">
					<bal-card-title level="x-large">${bm.id()}</bal-card-title>
					<bal-card-subtitle>HOST</bal-card-subtitle>
					<bal-card-subtitle>Description</bal-card-subtitle>
				</bal-card-content>
			</bal-card>
<%--
			<div class="row bm-row">
				<div class="col s3"></div>
				<div class="col s6">
					<div id="ogcard_${bm.id()}"
						 hx-trigger="revealed"
						 hx-get="/card/${bm.id()}"
						 hx-vals='{"id": "${bm.id()}"}"}'
						 hx-swap="outerHTML"
					>
						<div class="card bm-card">
							<div class="card-image">
								<img width="640"
									 height="336"
									 src="https://placehold.co/640x336/png?text=LOADING..."
									 alt='loading...'
								>
							</div>
							<div class="card-content">
								<span class="card-title">${bm.id()}</span>
								<div class="bm-host">HOST</div>
								<div class="bm-description">Description</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col s3"></div>
			</div>
--%>
		@endfor
	</bal-stack>
@endif
@if("card".equals(fragment))
	!{var card = fragmentHelper.getBookmarkRequestStore().getCardModel().card(); }
	<bal-card-content
	>
		<img class="mb-normal" src="https://placehold.co/640x336/png?text=LOADING...">
		<bal-card-title level="x-large">${card.ogTitle()}</bal-card-title>
		<bal-card-subtitle bold="true">${card.host()}</bal-card-subtitle>
		<bal-card-subtitle>${card.ogDescription()}</bal-card-subtitle>
		<bal-card-actions position="right">
			<bal-button square="true"
				_="on click send do_open_edit_modal to #edit-modal"
				icon="edit"
			></bal-button>
		</bal-card-actions>
<%--
		<bal-button-group position"right">
			<bal-button square="true" icon="edit"></bal-button>
		</bal-button-group>
--%>
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
@endif
