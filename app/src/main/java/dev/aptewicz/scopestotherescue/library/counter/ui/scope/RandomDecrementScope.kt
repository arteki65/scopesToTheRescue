package dev.aptewicz.scopestotherescue.library.counter.ui.scope

import dev.aptewicz.scopestotherescue.library.counter.domain.CounterAction
import dev.aptewicz.scopestotherescue.library.random.RandomGenerator
import dev.aptewicz.scopestotherescue.library.store.AppStore

interface RandomDecrementScope {
    val randomGenerator: RandomGenerator
    val store: AppStore

    fun onRandomDecrement() {
        store.dispatchAction(CounterAction.Decrement(randomGenerator.nextInt()))
    }
}
