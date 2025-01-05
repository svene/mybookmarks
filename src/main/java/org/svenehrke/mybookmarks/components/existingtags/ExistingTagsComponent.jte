@import org.svenehrke.mybookmarks.components.main.SearchByTagsAction
@import org.svenehrke.mybookmarks.components.existingtags.ExistingTagsComponent.Ctx
@import org.svenehrke.mybookmarks.components.existingtags.ExistingTagsComponent.TagAndCount

@param Ctx ctx

<bal-card-content>
    @for(TagAndCount tag : ctx.existingTagsSortedByName())
        !{var color = ctx.colorForTag(tag.tag());}
    <bal-tag color="${color}"
             hx-trigger="click"
             hx-put="${SearchByTagsAction.URL}"
             hx-vals='{"search_by_tags": "${tag.tag()}"}'
             hx-swap="none"
    >
        ${tag.tag()} ${tag.count()}
    </bal-tag>
    @endfor
</bal-card-content>
