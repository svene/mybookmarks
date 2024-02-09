<div class="s12 m6 input-field outlined">
	<input
		id="bm-url"
		name="bm-url"
		type="text"
		placeholder=" "
		maxlength="20"
		hx-put="/preview-url"
		hx-swap="none"
		hx-trigger="keyup delay:800ms changed"
	>
	<label for="last_name">New URL</label>
	<span class="supporting-text">Insert a URL to preview the new entry</span>
</div>

<%--
<div class="w50">
	<input

		id="bm-url"
		name="bm-url"
		hx-put="/preview-url"
		hx-swap="none"
		hx-trigger="keyup delay:800ms changed"
		placeholder="Insert URL"
	>
</div>
--%>
