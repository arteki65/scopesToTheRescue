package dev.aptewicz.scopestotherescue.library.counter.domain

import dev.aptewicz.scopestotherescue.library.store.Action

sealed class CounterAction : Action() {
    data class Increment(
        val by: Int,
    ) : CounterAction()

    data class Decrement(
        val by: Int,
    ) : CounterAction()

    data class Multiply(
        val by: Int,
    ) : CounterAction()
}
