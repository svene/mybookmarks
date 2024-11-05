@import org.svenehrke.mybookmarks.components.csvtext.CsvTextComponent.Ctx

@param Ctx ctx

<bal-card-subtitle>CSV</bal-card-subtitle>
<div>
    <style>
		me textarea {
			color: white;
			background-color: black;
			padding: 10px;
			margin-left: var(--bal-space-large)
		}
    </style>
    <textarea rows="6" cols="50" id="csvString" readonly wrap="off">
${ctx.convertBookmarksToCSV()}
    </textarea>
</div>
