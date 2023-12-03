@import gg.jte.Content

@param String init
@param Content content

<!doctype html>
<html lang="">
<head>
	<title>Bookmarks App</title>
	<link rel="stylesheet" href="/webjars/picocss__pico/2.0.0-alpha1/css/pico.css"/>

	<link rel="stylesheet" href="/main.css"/>

	<script src="/webjars/htmx.org/1.9.8/dist/htmx.js"></script>
	<script>
        htmx.logAll();
	</script>

</head>

<body hx-boost="true">
<main class="container">
	<nav class="w50">
		<ul>
			<li><strong>Bookmarks App</strong></li>
		</ul>
		<ul>
			<li><button onclick="copyCsvToClipboard()">Copy CSV</button></li>
			<li><button hx-post="/reload" hx-swap="none">Reload</button></li>
		</ul>
	</nav>

	${content}
</main>
</body>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        htmx.trigger("body", "${init}");
    });

    function copyCsvToClipboard() {
        // Get the text field
        var copyText = document.getElementById("csvString");
        // Select the text field
        copyText.select();
        copyText.setSelectionRange(0, 99999); // For mobile devices

        // Copy the text inside the text field
        navigator.clipboard.writeText(copyText.value);
    }
</script>
</html>
