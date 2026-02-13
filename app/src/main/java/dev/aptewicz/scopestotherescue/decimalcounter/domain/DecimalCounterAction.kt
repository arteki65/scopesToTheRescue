package dev.aptewicz.scopestotherescue.decimalcounter.domain

import dev.aptewicz.scopestotherescue.library.store.Action

sealed class DecimalCounterAction : Action() {
    data class Increment(
        val by: Int,
    ) : DecimalCounterAction()

    data class Decrement(
        val by: Int,
    ) : DecimalCounterAction()
}
