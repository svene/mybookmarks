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

	<script src="/webjars/htmx.org/1.9.8/dist/htmx.js"></script>
	<script src="/webjars/hyperscript.org/0.9.12/dist/_hyperscript.js"></script>
	<script>
        //htmx.logAll();
	</script>

</head>

<body hx-boost="false">
<bal-app>
	<header>
		<bal-navbar>
			<bal-navbar-menu>
				<bal-navbar-menu-start>
					<bal-tabs interface="navbar">
						<bal-tab-item value="tab-a" label="Tab A"></bal-tab-item>
						<bal-tab-item value="tab-b" label="Tab B"></bal-tab-item>
						<bal-tab-item value="tab-c" label="Tab C"></bal-tab-item>
					</bal-tabs>
				</bal-navbar-menu-start>
				<bal-navbar-menu-end>
					<bal-button-group>
						<bal-button color="light" inverted="true" icon="date">Date</bal-button>
						<bal-button color="light" inverted="true" icon="balIconCopy">Copy CSV</bal-button>
						<bal-button id="star-button" square="" color="light" inverted="true" icon="star-full"></bal-button>
						<bal-button id="date-button" square="" color="light" inverted="true" icon="date"></bal-button>
						<bal-button id="call-button" square="" color="light" inverted="true" icon="call"></bal-button>
					</bal-button-group>
				</bal-navbar-menu-end>
			</bal-navbar-menu>
		</bal-navbar>
	</header>
	<main class="container mt-xx-large">
		${content}
	</main>
</bal-app>
@if(1 > 2)
<nav>
	<div class="nav-wrapper">
		<a href="#" class="brand-logo"><i class="material-icons">cloud</i>My Bookmarks</a>
		<ul class="right hide-on-med-and-down">
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
					hx-post="${TriggerController.RELOAD_URL}" hx-swap="none"
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
@endif

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
