@import gg.jte.Content

@param String init
@param Content content

<!doctype html>
<html lang="">
<head>
	<title>Bookmarks App</title>
	<link
		rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/@baloise/design-system-css/css/baloise-design-system.css"
	/>
	<script
		type="module"
		src="https://cdn.jsdelivr.net/npm/@baloise/design-system-components/dist/design-system-components/design-system-components.esm.js"
	></script>
	<script type="module" src="https://cdn.jsdelivr.net/npm/@baloise/design-system-icons@15.2.4/dist/index.min.js"></script>
	<script
		nomodule
		src="https://cdn.jsdelivr.net/npm/@baloise/design-system-components/dist/design-system-components/design-system-components.js"
	></script>
	<link rel="stylesheet" href="/main.css"/>

	<script src="/main.js"></script>
	<script src="/webjars/htmx.org/1.9.8/dist/htmx.js"></script>
	<script>
        //htmx.logAll();
	</script>

</head>

<body hx-boost="true">
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
						<bal-button square="" color="light" inverted="true" icon="call"></bal-button>
					</bal-button-group>
				</bal-navbar-menu-end>
			</bal-navbar-menu>
		</bal-navbar>
	</header>
	<bal-stage color="green" size="small">
		<bal-stage-body>
			<bal-heading>Welcome to the</bal-heading>
			<bal-heading subtitle>Baloise Design System</bal-heading>
		</bal-stage-body>
	</bal-stage>
	<main class="container mt-xx-large">
		<p>
			The Baloise Design System consists of UI components and a clearly defined visual style, released as both code
			implementations and design artifacts to build any number of web applications.
		</p>
		<bal-button href="https://design.baloise.dev" target="_blank">
			Check out the Documentation</bal-button
		>
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
@endif

</body>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        //htmx.trigger("body", "${init}");
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
