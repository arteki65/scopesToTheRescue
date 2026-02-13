package dev.aptewicz.scopestotherescue.decimalcounter.ui

import androidx.lifecycle.ViewModel
import dev.aptewicz.scopestotherescue.decimalcounter.domain.DecimalCounterAction
import dev.aptewicz.scopestotherescue.decimalcounter.domain.DecimalCounterScreenState
import dev.aptewicz.scopestotherescue.library.store.AppStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DecimalCounterViewModel(
    private val store: AppStore,
) : ViewModel() {
    val decimalCounterScreenStateFlow: Flow<DecimalCounterScreenState> =
        store.state.map { it.decimalCounterScreenState }

    fun onIncrement(by: Int) {
        store.dispatchAction(DecimalCounterAction.Increment(by))
    }

    fun onDecrement(by: Int) {
        store.dispatchAction(DecimalCounterAction.Decrement(by))
    }
}
