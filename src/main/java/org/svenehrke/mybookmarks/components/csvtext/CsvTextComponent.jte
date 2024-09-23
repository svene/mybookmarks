@import org.svenehrke.mybookmarks.components.csvtext.CsvTextComponent.Ctx

@param Ctx ctx

<bal-card-content>
<textarea
        id="csvString"
        class="preview-csv">${ctx.csv()}
        hx-trigger="bookmarksChanged from:body"
		hx-get="/csv_textfield" hx-swap="outerHTML"
</textarea>
</bal-card-content>
