@import org.svenehrke.mybookmarks.model.Bookmark
@import org.svenehrke.mybookmarks.components.bookmarkrows.BookmarkRowsComponent.Ctx

@param Ctx ctx

<bal-stack layout="vertical" align="center">
    <style>
		me bal-card { max-width: 50%; min-width: 50%; }
    </style>
    @for(Bookmark bm : ctx.buildBookmarks().stream().limit(10).toList())
        <bal-card>
            ${ctx.ME().placeholderCardComponent.ctx(bm.id())}
        </bal-card>
    @endfor
</bal-stack>

