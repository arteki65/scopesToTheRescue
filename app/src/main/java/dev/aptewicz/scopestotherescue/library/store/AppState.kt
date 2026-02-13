package dev.aptewicz.scopestotherescue.library.store

import dev.aptewicz.scopestotherescue.decimalcounter.domain.DecimalCounterScreenState

data class AppState(
    val decimalCounterScreenState: DecimalCounterScreenState = DecimalCounterScreenState(),
)
