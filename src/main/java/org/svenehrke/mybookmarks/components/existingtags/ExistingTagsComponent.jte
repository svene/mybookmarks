@import org.svenehrke.mybookmarks.components.existingtags.ExistingTagsComponent.Ctx
@import org.svenehrke.mybookmarks.components.existingtags.ExistingTagsComponent.TagAndCount

@param Ctx ctx

<bal-card-subtitle>All tags</bal-card-subtitle>

<bal-card-content>
    @for(TagAndCount tag : ctx.existingTags())
    <bal-tag color="green"
             hx-trigger="click"
             hx-put="/search/tags"
             hx-vals='{"search_by_tags": "${tag.tag()}"}'
             hx-swap="none"
    >
        ${tag.count()} ${tag.tag()}
    </bal-tag>
    @endfor
</bal-card-content>
