@import org.svenehrke.mybookmarks.components.settings.SettingsComponent.Ctx
@import org.svenehrke.mybookmarks.components.search.SearchComponent
@import org.svenehrke.mybookmarks.components.main.ReloadAction
@import org.svenehrke.mybookmarks.components.csvtext.CsvTextComponent

@param Ctx ctx

<bal-card-content>
    <bal-card-content>
        <div id="csv-text">
            ${new CsvTextComponent.Ctx(ctx.bookmarkSessionService())}
        </div>
    </bal-card-content>
</bal-card-content>
<bal-card-actions position="right">
    <bal-button onclick="copyCsvToClipboard()" color="light" inverted="true" icon="copy">CSV</bal-button>
    <bal-button
            color="light"
            inverted="true"
            icon="refresh"
            hx-trigger="click"
            hx-post="${ReloadAction.URL}"
            hx-swap="none"
    >CSV
    </bal-button>
    <bal-button
            color="light"
            inverted="true"
            icon="back"
            hx-trigger="click"
            hx-get="${SearchComponent.URL}"
            hx-target="#first_card"
            hx-swap="innerHTML"
    >Done
    </bal-button>
</bal-card-actions>
