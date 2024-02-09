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
