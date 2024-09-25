@import org.svenehrke.mybookmarks.components.addbookmark.AddBookmarkComponent.Ctx
@import org.svenehrke.mybookmarks.components.newbookmark.NewBookmarkComponent

@param Ctx ctx

<bal-card-content>
    <bal-card-actions position="right">
        <bal-button
                square="true"
                icon="plus"
                hx-trigger="click"
                hx-get="${NewBookmarkComponent.URL}"
                hx-target="closest bal-card-content"
        ></bal-button>
    </bal-card-actions>
</bal-card-content>
