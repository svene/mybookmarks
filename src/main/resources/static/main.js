document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.tooltipped');
    var instances = M.Tooltip.init(elems, {
        // specify options here
    });

    var elems = document.querySelectorAll('.modal');
    var modals = M.Modal.init(elems, {
        // specify options here
    });

    var elems = document.querySelectorAll('.sidenav');
    var sidenavs = M.Sidenav.init(elems, {
        // specify options here
    });

    // var elems = document.querySelectorAll('select');
    // console.log('setup: initSelect');
    window.mbm = {};
    window.mbm.initSelect = (selector) => {
        // var elems = document.querySelectorAll('select');
        var elems = document.querySelectorAll(selector);
        var instances = M.FormSelect.init(elems, {
            // specify options here
        });
    };

});

