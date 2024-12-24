@import org.svenehrke.mybookmarks.components.search.SearchComponent.Ctx
@import org.svenehrke.mybookmarks.components.settings.SettingsComponent
@import org.svenehrke.mybookmarks.components.existingtags.ExistingTagsComponent

@param Ctx ctx

<bal-card-content>
    <bal-card-title level="x-large">Search</bal-card-title>
    <div id="existing-tags"
         class="mb-normal"
         hx-trigger="tags-changed from:body"
         hx-get="${ExistingTagsComponent.UI_URL}"
    >
        ${new ExistingTagsComponent.Ctx(ctx.bookmarkSessionService())}
    </div>
</bal-card-content>

<bal-card-actions position="right">
    <bal-icon
            name="settings"
            hx-get="${SettingsComponent.UI_URL}"
            hx-target="#first_card"
            hx-swap="innerHTML"
    ></bal-icon>
</bal-card-actions>
