@import org.svenehrke.mybookmarks.Bookmark
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

@if("edit-inline-form".equals(fragment))
	!{var card = fragmentHelper.getBookmarkRequestStore().getCardModel().card(); }
	<form
		hx-put="edit/inline/putbookmark"
		hx-target="closest bal-card-content"
		hx-swap="outerHTML"
	>
		<bal-form-grid>
			<bal-form-col>
				<input type="hidden" name="id" value="${card.id()}">
				<div class="field">
					<label class="label">URL</label>
					<div class="control">
						<input class="input" placeholder="URL" name="url" value="${card.url()}">
					</div>
					<p class="help">This username is available</p>
				</div>
				<div>
					<bal-field>
						<bal-field-label>Tags</bal-field-label>
						<bal-field-control>
							<bal-input
								name="tags"
								placeholder="Tags"
								value="${card.tagString()}"
							></bal-input>
						</bal-field-control>
					</bal-field>
				</div>
				<bal-input-group class="mb-normal" name="tags2">
					<bal-tag closable="true">Tag</bal-tag>
					<bal-tag closable="true">Tag</bal-tag>
					<bal-tag closable="true">Tag</bal-tag>
					<bal-input placeholder="Enter text here"></bal-input>
				</bal-input-group>

			</bal-form-col>

			<bal-form-col>
				<bal-button-group position="right" reverse="true">
					<bal-button color="text">
						Cancel
					</bal-button>
					<bal-button element-type="submit" color="primary">
						Submit
					</bal-button>
				</bal-button-group>
			</bal-form-col>

		</bal-form-grid>
	</form>

@endif


