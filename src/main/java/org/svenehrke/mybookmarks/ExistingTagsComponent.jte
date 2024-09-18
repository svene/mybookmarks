@import org.svenehrke.mybookmarks.ExistingTagsComponent.Ctx

@param Ctx ctx

<bal-card style="max-width: 50%; min-width: 50%">
    <bal-card-content>
        <bal-card-title level="x-large">Existing tags</bal-card-title>
        <bal-tag-group>
            @for(String tag : ctx.tags())
                <bal-tag>${tag}</bal-tag>
            @endfor
        </bal-tag-group>
    </bal-card-content>
</bal-card>
