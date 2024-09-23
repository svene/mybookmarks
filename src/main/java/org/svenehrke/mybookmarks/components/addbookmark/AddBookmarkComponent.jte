@import org.svenehrke.mybookmarks.components.addbookmark.AddBookmarkComponent.Ctx

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
