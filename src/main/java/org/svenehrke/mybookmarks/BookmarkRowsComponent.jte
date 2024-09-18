@import org.svenehrke.mybookmarks.Bookmark
@import org.svenehrke.mybookmarks.BookmarkRowsComponent.Ctx

@param Ctx ctx
!{var bookmarks = ctx.bookmarks(); }

<bal-stack layout="vertical" align="center">
    @for(Bookmark bm : bookmarks)
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
    <%--
                <div class="row bm-row">
                    <div class="col s3"></div>
                    <div class="col s6">
                        <div id="ogcard_${bm.id()}"
                             hx-trigger="revealed"
                             hx-get="/card/${bm.id()}"
                             hx-vals='{"id": "${bm.id()}"}"}'
                             hx-swap="outerHTML"
                        >
                            <div class="card bm-card">
                                <div class="card-image">
                                    <img width="640"
                                         height="336"
                                         src="https://placehold.co/640x336/png?text=LOADING..."
                                         alt='loading...'
                                    >
                                </div>
                                <div class="card-content">
                                    <span class="card-title">${bm.id()}</span>
                                    <div class="bm-host">HOST</div>
                                    <div class="bm-description">Description</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col s3"></div>
                </div>
    --%>
    @endfor
</bal-stack>
