@import org.svenehrke.mybookmarks.components.existingtags.ExistingTagsComponent.Ctx

@param Ctx ctx

<bal-card-subtitle>All tags</bal-card-subtitle>

<bal-card-content>
    @for(String tag : ctx.existingTags())
        <bal-tag color="green" hx-trigger="click" hx-put="tagchange">
            ${tag}
        </bal-tag>
    @endfor
</bal-card-content>
