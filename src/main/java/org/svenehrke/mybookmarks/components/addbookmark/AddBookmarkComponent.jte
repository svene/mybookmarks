@import org.svenehrke.mybookmarks.components.addbookmark.AddBookmarkComponent.Ctx
@import org.svenehrke.mybookmarks.components.newbookmark.NewBookmarkFormComponent

@param Ctx ctx

<bal-card-actions position="center">
    <bal-button
            square="true"
            icon="plus"
            hx-trigger="click"
            hx-get="${NewBookmarkFormComponent.URL}"
            hx-target="closest bal-card-content"
    ></bal-button>
</bal-card-actions>
