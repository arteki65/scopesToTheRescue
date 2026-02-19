package dev.aptewicz.scopestotherescue.binarycounter.ui.scope

import dev.aptewicz.scopestotherescue.library.counter.ui.scope.DecrementScope
import dev.aptewicz.scopestotherescue.library.counter.ui.scope.IncrementScope

interface BinaryCounterScope :
    IncrementScope,
    DecrementScope
