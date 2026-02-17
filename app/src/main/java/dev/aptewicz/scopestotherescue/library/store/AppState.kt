package dev.aptewicz.scopestotherescue.library.store

import dev.aptewicz.scopestotherescue.library.counter.domain.CounterState

data class AppState(
    val counterState: CounterState = CounterState(),
)
