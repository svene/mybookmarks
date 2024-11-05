
// importing from .../dist/index.js does not work: caused by transitive import:
//  .../dist/index.js
//  -> import esm-es5/index.js: import{__awaiter,__extends,__generator} from "tslib";
// :
// import { initialize } from '/webjars/baloise-design-system/@baloise/ds-core/dist/index.js'

// Therefore 'import from .../esm/index.js' is used which is working:
import { initialize, waitForDesignSystem, waitForComponent } from '/webjars/baloise-design-system/@baloise/ds-core/dist/esm/index.js'

import {
    balIconStarFull,
    balIconCopy,
    balIconCall,
    balIconRefresh,
    balIconLink,
    balIconSettings,
    balIconBack,
} from '/webjars/baloise-design-system/@baloise/ds-icons/index.esm.js'

// Make non-built-in icons available to the html for usage:
initialize({
    // Hint: to use them in the name attributes BDS changes the names. E.g.: balIconCopy -> copy, balIconStarFull -> startFull
    icons: { balIconStarFull, balIconCopy, balIconCall, balIconRefresh, balIconLink, balIconSettings, balIconBack },
});

