@import org.svenehrke.mybookmarks.components.existingtags.ExistingTags.Ctx

@param Ctx ctx

<bal-card-content app-id="existing_tags">
    <bal-card-title level="x-large">Existing tags</bal-card-title>
    @for(String tag : ctx.tags())
        <bal-tag>${tag}</bal-tag>
    @endfor
</bal-card-content>
