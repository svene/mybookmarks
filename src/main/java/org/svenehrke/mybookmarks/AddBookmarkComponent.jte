@import org.svenehrke.mybookmarks.AddBookmarkComponent.Ctx

@param Ctx ctx

<bal-card-content>
    <bal-card-actions position="right">
        <bal-button
                square="true"
                icon="plus"
                hx-trigger="click"
                hx-get="/newbookmark/form"
                hx-target="closest bal-card-content"
        ></bal-button>
    </bal-card-actions>
</bal-card-content>

<%--
	<div class="col offset-s8 s1 mb-2 right-align">
		<a class="btn-floating btn-large waves-effect waves-light"
		   hx-trigger="click"
		   hx-get="/fragments?fragment=new-url"
		   hx-target="#add-or-preview"
		>
			<i class="material-icons">add</i>
		</a>
	</div>
--%>
