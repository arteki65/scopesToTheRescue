package dev.aptewicz.scopestotherescue.decimalcounter.ui

import androidx.lifecycle.ViewModel
import dev.aptewicz.scopestotherescue.decimalcounter.ui.scope.DecimalCounterScope
import dev.aptewicz.scopestotherescue.library.counter.ui.CounterScreenState
import dev.aptewicz.scopestotherescue.library.random.RandomGenerator
import dev.aptewicz.scopestotherescue.library.store.AppStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DecimalCounterViewModel(
    override val store: AppStore,
    override val randomGenerator: RandomGenerator,
) : ViewModel(),
    DecimalCounterScope {
    val counterScreenStateFlow: Flow<CounterScreenState> =
        store.state.map {
            CounterScreenState(
                counterValue = it.counterState.counterValue.toString(),
            )
        }
}
