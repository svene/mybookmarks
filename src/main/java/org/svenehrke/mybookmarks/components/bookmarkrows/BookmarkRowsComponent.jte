@import org.svenehrke.mybookmarks.model.Bookmark
@import org.svenehrke.mybookmarks.components.bookmarkrows.BookmarkRowsComponent.Ctx

@param Ctx ctx
!{var bookmarks = ctx.bookmarks(); }

<bal-stack layout="vertical" align="center">
    @for(Bookmark bm : bookmarks.stream().limit(10).toList())
        <bal-card style="max-width: 50%; min-width: 50%">
            ${ctx.placeholderCardComponent().render(bm.id())}
        </bal-card>
    @endfor
</bal-stack>
