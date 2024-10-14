@import org.svenehrke.mybookmarks.model.Bookmark
@import org.svenehrke.mybookmarks.components.bookmarkrows.BookmarkRowsComponent.Ctx

@param Ctx ctx
!{var bookmarks = ctx.bookmarks(); }

<bal-stack layout="vertical" align="center">
    <style>
		me bal-card { max-width: 50%; min-width: 50%; }
    </style>
    @for(Bookmark bm : bookmarks.stream().limit(10).toList())
        <bal-card>
            ${ctx.placeholderCardComponent().ctx(bm.id())}
        </bal-card>
    @endfor
</bal-stack>
