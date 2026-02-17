package dev.aptewicz.scopestotherescue.decimalcounter.ui

import androidx.lifecycle.ViewModel
import dev.aptewicz.scopestotherescue.decimalcounter.ui.scope.DecimalCounterScope
import dev.aptewicz.scopestotherescue.library.counter.domain.CounterAction
import dev.aptewicz.scopestotherescue.library.counter.ui.CounterScreenState
import dev.aptewicz.scopestotherescue.library.random.RandomGenerator
import dev.aptewicz.scopestotherescue.library.store.AppStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DecimalCounterViewModel(
    private val store: AppStore,
    private val randomGenerator: RandomGenerator,
) : ViewModel(),
    DecimalCounterScope {
    val counterScreenStateFlow: Flow<CounterScreenState> =
        store.state.map {
            CounterScreenState(
                counterValue = it.counterState.counterValue.toString(),
            )
        }

    override fun onIncrement(by: Int) {
        store.dispatchAction(CounterAction.Increment(by))
    }

    override fun onDecrement(by: Int) {
        store.dispatchAction(CounterAction.Decrement(by))
    }

    override fun onMultiply(by: Int) {
        store.dispatchAction(CounterAction.Multiply(by))
    }

    override fun onRandomDecrement() {
        store.dispatchAction(CounterAction.Decrement(randomGenerator.nextInt()))
    }

    override fun onRandomIncrement() {
        store.dispatchAction(CounterAction.Increment(randomGenerator.nextInt()))
    }

    override fun onReset() {
        store.dispatchAction(CounterAction.Reset)
    }
}
