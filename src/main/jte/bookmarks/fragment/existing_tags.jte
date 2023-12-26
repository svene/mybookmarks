@import org.svenehrke.mybookmarks.FragmentHelper

@param FragmentHelper fragmentHelper
!{var M = FragmentHelper.ExistingTagsModel.build(fragmentHelper); }

<details class="w50">
	<summary>Tags</summary>
	<ul>
		@for(String tag : M.tags())
			<li>${tag}</li>
		@endfor
	</ul>
</details>
