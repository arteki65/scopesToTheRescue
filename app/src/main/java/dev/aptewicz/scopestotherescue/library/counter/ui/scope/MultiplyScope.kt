package dev.aptewicz.scopestotherescue.library.counter.ui.scope

import dev.aptewicz.scopestotherescue.library.counter.domain.CounterAction
import dev.aptewicz.scopestotherescue.library.store.AppStore

interface MultiplyScope {
    val store: AppStore

    fun onMultiply(by: Int) {
        store.dispatchAction(CounterAction.Multiply(by))
    }
}
