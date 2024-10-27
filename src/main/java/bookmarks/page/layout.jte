@import gg.jte.Content
@import org.svenehrke.mybookmarks.controller.TriggerController

@param String init
@param Content content

<!doctype html>
<html lang="">
<head>
	<title>Bookmarks App</title>
	<link rel="stylesheet" href="/webjars/baloise-design-system/@baloise/ds-styles/css/all.min.css" />
	<script type="module" src="/webjars/baloise-design-system/@baloise/ds-core/dist/baloise-design-system/baloise-design-system.esm.js"></script>
	<script nomodule src="/webjars/baloise-design-system/@baloise/ds-core/dist/baloise-design-system/baloise-design-system.js"></script>

	<link rel="stylesheet" href="/assets/main.css"/>
	<script type="module" src="/assets/main.js" defer></script>

	<script src="/webjars/htmx.org/2.0.2/dist/htmx.js"></script>
	<script src="/webjars/hyperscript.org/0.9.12/dist/_hyperscript.js"></script>
	<script src="/assets/css-scope-inline.js"></script>
	<script>
        //htmx.logAll();
	</script>

</head>

<body hx-boost="false">
<bal-app>
	<header>
	</header>
	<main class="container mt-xx-large">
		${content}
	</main>
</bal-app>

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
