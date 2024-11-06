@import org.svenehrke.mybookmarks.components.search.SearchComponent.Ctx
@import org.svenehrke.mybookmarks.components.settings.SettingsComponent
@import org.svenehrke.mybookmarks.components.existingtags.ExistingTagsComponent

@param Ctx ctx

<bal-card-content>
    <bal-card-title level="x-large">Search</bal-card-title>
    <div id="existing-tags" class="mb-normal">
        ${new ExistingTagsComponent.Ctx(ctx.bookmarkSessionService())}
    </div>
</bal-card-content>

<bal-card-content>
    <bal-field>
        <bal-field-label>Search</bal-field-label>
        <bal-field-hint subject="Search by Tag">Comma separated List of tags. E.g.: htmx,css,-angular. Use a - prefix for exclusion</bal-field-hint>
        <bal-input id="search-tags"
                   name="search_by_tags"
                   type="search"
                   placeholder="Search tags"
                   hx-trigger="keyup delay:300ms"
                   hx-put="/search/tags"
                   hx-swap="none"
                   hx-include="#search-tags"
        ></bal-input>
    </bal-field>
</bal-card-content>

<bal-card-actions position="right">
    <bal-icon
            name="settings"
            hx-get="${SettingsComponent.UI_URL}"
            hx-target="#first_card"
            hx-swap="innerHTML"
    ></bal-icon>
</bal-card-actions>
