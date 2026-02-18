package dev.aptewicz.scopestotherescue.library.counter.ui.scope

import dev.aptewicz.scopestotherescue.library.counter.domain.CounterAction
import dev.aptewicz.scopestotherescue.library.store.AppStore

interface ResetScope {
    val store: AppStore

    fun onReset() {
        store.dispatchAction(CounterAction.Reset)
    }
}
