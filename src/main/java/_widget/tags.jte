@import java.util.List

@param List<String> tags

<bal-card-content>
    @for(String tag : tags)
        <bal-tag>${tag}</bal-tag>
    @endfor
</bal-card-content>
