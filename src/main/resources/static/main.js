document.addEventListener('DOMContentLoaded', function() {
    window.mbm = {};
    window.mbm.initSelect = (selector) => {
        var elems = document.querySelectorAll(selector);
        var instances = M.FormSelect.init(elems, {
            // specify options here
        });
    };

});

