package dev.aptewicz.scopestotherescue.binarycounter.ui

import androidx.lifecycle.ViewModel
import dev.aptewicz.scopestotherescue.binarycounter.ui.scope.BinaryCounterScope
import dev.aptewicz.scopestotherescue.library.counter.ui.CounterScreenState
import dev.aptewicz.scopestotherescue.library.store.AppStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BinaryCounterViewModel(
    override val store: AppStore,
) : ViewModel(),
    BinaryCounterScope {
    val counterScreenStateFlow: Flow<CounterScreenState> =
        store.state.map {
            CounterScreenState(
                counterValue = it.counterState.counterValue.toString(2),
            )
        }
}
