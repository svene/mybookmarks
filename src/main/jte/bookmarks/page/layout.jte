@import gg.jte.Content

@param String init
@param Content content

<!doctype html>
<html lang="">
<head>
	<title>Bookmarks App</title>
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link type="text/css" rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@materializecss/materialize@2.0.3-alpha/dist/css/materialize.min.css"/>
	<link rel="stylesheet" href="/main.css"/>

	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/@materializecss/materialize@2.0.3-alpha/dist/js/materialize.min.js"></script>
	<script src="/main.js"></script>
	<script src="/webjars/htmx.org/1.9.8/dist/htmx.js"></script>
	<script>
        //htmx.logAll();
	</script>

</head>

<body hx-boost="true">
<nav>
	<div class="nav-wrapper">
		<a href="#" class="brand-logo"><i class="material-icons">cloud</i>My Bookmarks</a>
		<ul class="right hide-on-med-and-down">
<%--			<button class="waves-effect waves-light btn" onclick="copyCsvToClipboard()">Copy CSV</button>--%>
<%--			<li><a href="sass.html"><i class="material-icons">search</i></a></li>--%>
			<li>
				<a
					href="#"
					class="tooltipped"
					data-position="bottom"
					data-tooltip="Copy CSV"
				>
					<i class="material-icons" onclick="copyCsvToClipboard()">content_copy</i>
				</a>
			</li>
			<li>
				<a
					href="#"
					class="tooltipped"
					data-position="bottom"
					data-tooltip="Reload"
					hx-post="/reload" hx-swap="none"
				>
					<i class="material-icons">refresh</i>
				</a>
			</li>
<%--			<li><a href="mobile.html"><i class="material-icons">more_vert</i></a></li>--%>
		</ul>
	</div>
</nav>

<div class="container mt-2">
	${content}
</div>
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
