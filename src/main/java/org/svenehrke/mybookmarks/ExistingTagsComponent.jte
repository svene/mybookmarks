@import org.svenehrke.mybookmarks.ExistingTagsComponent.Ctx

@param Ctx ctx

<bal-card-content>
    <bal-card-title level="x-large">Existing tags</bal-card-title>
        @for(String tag : ctx.tags())
            <bal-tag>${tag}</bal-tag>
        @endfor
</bal-card-content>
