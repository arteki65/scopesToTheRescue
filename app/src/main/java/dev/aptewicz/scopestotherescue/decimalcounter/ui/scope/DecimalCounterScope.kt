package dev.aptewicz.scopestotherescue.decimalcounter.ui.scope

import dev.aptewicz.scopestotherescue.library.counter.ui.scope.DecrementScope
import dev.aptewicz.scopestotherescue.library.counter.ui.scope.IncrementScope
import dev.aptewicz.scopestotherescue.library.counter.ui.scope.MultiplyScope
import dev.aptewicz.scopestotherescue.library.counter.ui.scope.RandomDecrementScope
import dev.aptewicz.scopestotherescue.library.counter.ui.scope.RandomIncrementScope
import dev.aptewicz.scopestotherescue.library.counter.ui.scope.ResetScope

interface DecimalCounterScope :
    IncrementScope,
    DecrementScope,
    MultiplyScope,
    RandomDecrementScope,
    RandomIncrementScope,
    ResetScope
