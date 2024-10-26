
// importing from .../dist/index.js does not work: caused by transitive import:
//  .../dist/index.js
//  -> import esm-es5/index.js: import{__awaiter,__extends,__generator} from "tslib";
// :
// import { initialize } from '/webjars/baloise-design-system/@baloise/ds-core/dist/index.js'

// Therefore 'import from .../esm/index.js' is used which is working:
import { initialize, waitForDesignSystem, waitForComponent } from '/webjars/baloise-design-system/@baloise/ds-core/dist/esm/index.js'

import { balIconStarFull, balIconCopy, balIconCall, balIconRefresh } from '/webjars/baloise-design-system/@baloise/ds-icons/index.esm.js'

console.log('main.js');

// Make non-built-in icons available to the html for usage:
initialize({
    // Hint: to use them in the name attributes BDS changes the names. E.g.: balIconCopy -> copy, balIconStarFull -> startFull
    icons: { balIconStarFull, balIconCopy, balIconCall, balIconRefresh },
});


console.log('main.js');

let el = document.querySelector('#call-button');
console.log('should be defined: (#call-button):', el); // works bc. it is defined in layout.html

// The following will not be found because it is not yet rendered.
// It will be rendered later by the design-system:
el = document.querySelector('#call-button bal-icon');
console.log('should be null: (#call-button bal-icon):', el); // will output null
// el.svg = balIconCall;

// Therefore: use the 'waitForDesignSystem()' callback to make it work:
waitForDesignSystem().then(() => {
    console.log('waitForDesignSystem.then()');
    el = document.querySelector('#call-button bal-icon');
    console.log('should be defined: (#call-button bal-icon):', el);
    // imperative (not used atm):
    //document.querySelector('#call-button bal-icon').svg = balIconCall;
});
