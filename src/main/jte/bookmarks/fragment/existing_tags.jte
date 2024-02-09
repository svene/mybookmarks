@import org.svenehrke.mybookmarks.FragmentHelper

@param FragmentHelper fragmentHelper
!{var M = FragmentHelper.ExistingTagsModel.build(fragmentHelper); }

<div class="row" hx-on:htmx:load="window.mbm.initSelect('#tag-select')">
	<div class="input-field col s4 offset-s3">
		<select id="tag-select" name="tag" multiple hx-put="/search/taglist" hx-swap="none">
			<option value="htmx">HTMX</option>
			<option value="sql">SQL</option>
			<option value="3">Option 3</option>
			<option value="4">Option 4</option>
			<option value="5">Option 5</option>
			<option value="6">Option 6</option>
			<option value="7">Option 7</option>
			<option value="8">Option 8</option>
		</select>
		<label for="form-select-2">Select Tags</label>
	</div>
</div>

<%--
<details class="w50">
	<summary>Tags</summary>
	<ul>
		@for(String tag : M.tags())
			<li>${tag}</li>
		@endfor
	</ul>
</details>
--%>
