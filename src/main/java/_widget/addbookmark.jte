@import org.svenehrke.mybookmarks.components.newbookmark.NewBookmarkComponent

    <bal-card-actions position="center">
        <bal-button
                square="true"
                icon="plus"
                hx-trigger="click"
                hx-get="${NewBookmarkComponent.URL}"
                hx-target="closest bal-card-content"
        ></bal-button>
    </bal-card-actions>
