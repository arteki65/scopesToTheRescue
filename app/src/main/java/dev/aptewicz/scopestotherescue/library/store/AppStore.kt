package dev.aptewicz.scopestotherescue.library.store

import dev.aptewicz.scopestotherescue.decimalcounter.domain.DecimalCounterAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AppStore {
    companion object {
        lateinit var instance: AppStore

        fun init() {
            instance = AppStore()
        }
    }

    private constructor()

    /*
         New in Kotlin 2.3.0
         Equal to
         private val _city = MutableStateFlow<String>("")
         val city: StateFlow<String> get() = _city
     */
    val state: StateFlow<AppState>
        field = MutableStateFlow(AppState())

    fun dispatchAction(action: Action) {
        state.update { previousState ->
            when (action) {
                is DecimalCounterAction.Increment -> {
                    previousState.copy(
                        decimalCounterScreenState =
                            previousState.decimalCounterScreenState.copy(
                                counterValue = previousState.decimalCounterScreenState.counterValue + action.by,
                            ),
                    )
                }

                is DecimalCounterAction.Decrement -> {
                    previousState.copy(
                        decimalCounterScreenState =
                            previousState.decimalCounterScreenState.copy(
                                counterValue = previousState.decimalCounterScreenState.counterValue - action.by,
                            ),
                    )
                }

                is DecimalCounterAction.Multiply -> {
                    previousState.copy(
                        decimalCounterScreenState =
                            previousState.decimalCounterScreenState.copy(
                                counterValue = previousState.decimalCounterScreenState.counterValue * action.by,
                            ),
                    )
                }

                else -> {
                    previousState
                }
            }
        }
    }
}
