package dev.aptewicz.scopestotherescue.library.counter.ui.scope

import dev.aptewicz.scopestotherescue.library.counter.domain.CounterAction
import dev.aptewicz.scopestotherescue.library.store.AppStore

interface DecrementScope {
    val store: AppStore

    fun onDecrement(by: Int) {
        store.dispatchAction(CounterAction.Decrement(by))
    }
}
