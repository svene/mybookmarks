@import org.svenehrke.mybookmarks.CsvInfo

@param String csvString
@param CsvInfo csvInfo

<!doctype html>
<html lang="">
<title>CSV Preview</title>
<head>
</head>
<body>
	<textarea rows="${csvInfo.records().size() + 2}" cols="${csvInfo.maxCol() + 2}" class="preview-csv">${csvString}</textarea>
</body>
</html>
