@import org.svenehrke.mybookmarks.components.existingtags.ExistingTags
@import org.svenehrke.mybookmarks.components.existingtags.ExistingTags.Ctx

@param Ctx ctx

<bal-card-content hx-trigger="bookmarksChanged from:body" hx-get="${ExistingTags.URL}" hx-swap="outerHTML">
    <bal-card-title level="x-large">Existing tags</bal-card-title>
    @for(String tag : ctx.tags())
        <bal-tag>${tag}</bal-tag>
    @endfor
</bal-card-content>
