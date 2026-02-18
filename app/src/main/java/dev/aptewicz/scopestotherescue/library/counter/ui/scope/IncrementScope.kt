package dev.aptewicz.scopestotherescue.library.counter.ui.scope

import dev.aptewicz.scopestotherescue.library.counter.domain.CounterAction
import dev.aptewicz.scopestotherescue.library.store.AppStore

interface IncrementScope {
    val store: AppStore

    fun onIncrement(by: Int) {
        store.dispatchAction(CounterAction.Increment(by))
    }
}
