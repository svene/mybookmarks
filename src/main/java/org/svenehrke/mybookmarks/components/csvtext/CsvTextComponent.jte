@import org.svenehrke.mybookmarks.components.csvtext.CsvTextComponent.Ctx

@param Ctx ctx

<bal-card-content>
    <style>
        me textarea {color: white; background-color: black;padding: 10px}
    </style>
<textarea rows="6" cols="50" id="csvString" readonly wrap="off">
${ctx.csv()}
</textarea>
</bal-card-content>
