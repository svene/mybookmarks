@import org.svenehrke.mybookmarks.Bookmark

@template.bookmarks.page.layout(
init = "init",
content = @`

	<form>
		@if(false)
		<div class="row">
			<div class="col s6 offset-s3">
				<input
					id="search_by_tags"
					type="search"
					name="search_by_tags"
					placeholder="tag,tag,..."
					hx-put="/search/tags"
					hx-swap="none"
					hx-trigger="keyup delay:800ms changed"
				/>
			</div>
		</div>
		<div class="row">
			<div class="col s6 offset-s3 mt-2">
				<input
					type="search"
					name="search_by_title"
					placeholder="Title"
					hx-get="/food/search"
					hx-target="#search-result"
					hx-trigger="keyup delay:800ms changed"
				/>
			</div>
		</div>
		@endif
	</form>

	<%--	================================================== --%>
	@if(false)
	<!-- Modal Structure -->
	<div id="modal1" class="modal">
		<div class="modal-content">
			<h4>Tags</h4>
			<p>Selected</p>
			<p>Available</p>
			<div class="chip">HTMX</div>
			<div class="chip"
				 hx-get="/search/bla"
				 hx-swap="none"
				 hx-trigger="click"
			>SQL</div>
<%--
			<form action="#">
				<span><label><input type="checkbox" /><span>Red</span></label></span>
				<span><label><input type="checkbox" /><span>Blue</span></label></span>
			</form>
--%>
		</div>
		<div class="modal-footer">
			<a href="#!" class="modal-close waves-effect btn-flat">Close</a>
		</div>
	</div>

	<!-- Modal Trigger -->
	<button data-target="modal1" class="btn modal-trigger">Modal</button>
	@endif

	<%--	================================================== --%>
	@if(false)
	<ul id="slide-out" class="sidenav">
		<li><a href="#!"><i class="material-icons">cloud</i>First Link With Icon</a></li>
		<li><a href="#!">Second Link</a></li>
		<li><div class="divider"></div></li>
		<li><a class="subheader">Subheader</a></li>
		<li><a class="waves-effect" href="#!">Third Link With Waves</a></li>
		<li class="ml-2"><label><input type="checkbox" /><span>Red</span></label></li>
	</ul>
	<!-- Trigger -->
	<a href="#" data-target="slide-out" class="sidenav-trigger"><i class="material-icons">menu</i></a>
	@endif
	<%--	================================================== --%>
	<%--	================================================== --%>
	<div hx-trigger="init from:body, bookmarksChanged from:body" hx-get="/fragment?path=existing_tags">[Tags]</div>
	<hr class="w50">

	<div id="csvTextfield" hx-trigger="init from:body, bookmarksChanged from:body" hx-get="/fragment?path=csv_textfield"></div>


	<div class="row">
		<div class="col s1 offset-s8 mb-2">
			<a class="btn-floating btn-large waves-effect waves-light">
				<i class="material-icons">add</i>
			</a>
		</div>
	</div>

    <%-- New URL --%>
	<div class="row">
		<div class="col s12">
			<div hx-trigger="init from:body, bookmarksChanged from:body" hx-get="/fragment?path=new-url"></div>
		</div>
	</div>

    <%-- Preview --%>
	<div class="row">
		<div class="col s12">
			<div hx-trigger="newPreview from:body, bookmarksChanged from:body" hx-get="/fragment?path=preview_card"></div>
		</div>
	</div>

	<div id="search-result" hx-trigger="init from:body, bookmarksChanged from:body, searchTagsChanged from:body" hx-get="/fragment?path=bookmark_rows"></div>
`)
