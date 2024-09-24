@import org.svenehrke.mybookmarks.model.Bookmark
@import org.svenehrke.mybookmarks.components.bookmarkrows.BookmarkRowsComponent.Ctx

@param Ctx ctx
!{var bookmarks = ctx.bookmarks(); }

<bal-stack layout="vertical" align="center">
    @for(Bookmark bm : bookmarks.stream().limit(10).toList())
        <bal-card style="max-width: 50%; min-width: 50%">
            <bal-card-content id="ogcard_${bm.id()}"
                              hx-trigger="revealed"
                              hx-get="/card/${bm.id()}"
                              hx-vals='{"id": "${bm.id()}"}"}'
                              hx-swap="outerHTML"
            >
                <img class="mb-normal" src="https://placehold.co/640x336/png?text=LOADING...">
                <bal-card-title level="x-large">${bm.id()}</bal-card-title>
                <bal-card-subtitle>HOST</bal-card-subtitle>
                <bal-card-subtitle>Description</bal-card-subtitle>
            </bal-card-content>
        </bal-card>
    @endfor
</bal-stack>
