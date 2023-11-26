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

<%--
	<style>
        body {
            padding: 1em;
        }
	</style>
--%>

</head>
<body hx-boost="true">
<main class="ui container">
	<header>
		<h1 class="ui header">Bookmarks App</h1>
	</header>

	${content}
</main>
</body>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        htmx.trigger("body", "${init}");
    });
</script>
</html>
