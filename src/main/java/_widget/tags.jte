@import java.util.List

@param List<String> tags

<bal-card-content>
    <bal-card-title level="x-large">Existing tags</bal-card-title>
    @for(String tag : tags)
        <bal-tag>${tag}</bal-tag>
    @endfor
</bal-card-content>
