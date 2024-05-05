
// importing from .../dist/index.js does not work: caused by transitive import:
//  .../dist/index.js
//  -> import esm-es5/index.js: import{__awaiter,__extends,__generator} from "tslib";
// :
// import { initialize } from '/webjars/baloise-design-system/16.1.0/@baloise/ds-core/dist/index.js'

// Therefore: import from .../esm/index.js which is working:
import { initialize, waitForDesignSystem, waitForComponent } from '/webjars/baloise-design-system/16.1.0/@baloise/ds-core/dist/esm/index.js'

import { balIconStarFull, balIconCall } from './webjars/baloise-design-system/16.1.0/@baloise/ds-icons/index.esm.js'

console.log('main.js');

// this is for only activating a certain set of icons (I think):
// initialize({
//     icons: { balIconStarFull },
// });


console.log('main.js');

let el = document.querySelector('#call-button');
console.log('el:', el); // works bc. it is defined in layout.html

// The following will not be found because it is not yet rendered.
// It will be rendered later by the design-system:
el = document.querySelector('#call-button bal-icon');
console.log('el:', el); // will output null
// el.svg = balIconCall;

// Therefore: use the 'waitForDesignSystem()' callback to make it work:
// waitForDesignSystem(document.querySelector('#call-button')).then(() => {
waitForDesignSystem().then(() => {
    console.log('waitForDesignSystem.then()');
    el = document.querySelector('#call-button bal-icon');
    console.log('el:', el);
    document.querySelector('#call-button bal-icon').svg = balIconCall;
});
