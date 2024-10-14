@import org.svenehrke.mybookmarks.components.csvtext.CsvTextComponent.Ctx

@param Ctx ctx

<bal-card-content>
<textarea
        id="csvString"
        class="preview-csv">${ctx.csv()}
</textarea>
</bal-card-content>
