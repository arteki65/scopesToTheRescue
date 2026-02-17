package dev.aptewicz.scopestotherescue.binarycounter.ui

import androidx.lifecycle.ViewModel
import dev.aptewicz.scopestotherescue.library.counter.domain.CounterAction
import dev.aptewicz.scopestotherescue.library.counter.ui.CounterScreenState
import dev.aptewicz.scopestotherescue.library.store.AppStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BinaryCounterViewModel(
    private val store: AppStore,
) : ViewModel() {
    val counterScreenStateFlow: Flow<CounterScreenState> =
        store.state.map {
            CounterScreenState(
                counterValue = it.counterState.counterValue.toString(2),
            )
        }

    fun onIncrement(by: Int) {
        store.dispatchAction(CounterAction.Increment(by))
    }

    fun onDecrement(by: Int) {
        store.dispatchAction(CounterAction.Decrement(by))
    }

    fun onReset() {
        store.dispatchAction(CounterAction.Reset)
    }
}
