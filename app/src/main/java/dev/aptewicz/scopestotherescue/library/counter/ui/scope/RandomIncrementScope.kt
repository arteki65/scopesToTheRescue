package dev.aptewicz.scopestotherescue.library.counter.ui.scope

import dev.aptewicz.scopestotherescue.library.counter.domain.CounterAction
import dev.aptewicz.scopestotherescue.library.random.RandomGenerator
import dev.aptewicz.scopestotherescue.library.store.AppStore

interface RandomIncrementScope {
    val randomGenerator: RandomGenerator
    val store: AppStore

    fun onRandomIncrement() {
        store.dispatchAction(CounterAction.Increment(randomGenerator.nextInt()))
    }
}
