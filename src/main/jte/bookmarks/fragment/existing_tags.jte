@import org.svenehrke.mybookmarks.FragmentHelper

@param FragmentHelper fragmentHelper
!{var M = FragmentHelper.ExistingTagsModel.build(fragmentHelper); }

<div class="row" hx-on:htmx:load="window.mbm.initSelect('#tag-select')">
	<div class="input-field col s4 offset-s3">
		<select id="tag-select" name="tag" multiple hx-put="/search/taglist" hx-swap="none">
			@for(String tag : M.tags())
				<option value="${tag}">${tag}</option>
				<li>${tag}</li>
			@endfor
		</select>
		<label for="form-select-2">Select Tags</label>
	</div>
</div>
