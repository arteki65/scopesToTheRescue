package dev.aptewicz.scopestotherescue.library.store

import dev.aptewicz.scopestotherescue.library.counter.domain.CounterAction
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
                is CounterAction.Increment -> {
                    previousState.copy(
                        counterState =
                            previousState.counterState.copy(
                                counterValue = previousState.counterState.counterValue + action.by,
                            ),
                    )
                }

                is CounterAction.Decrement -> {
                    previousState.copy(
                        counterState =
                            previousState.counterState.copy(
                                counterValue = previousState.counterState.counterValue - action.by,
                            ),
                    )
                }

                is CounterAction.Multiply -> {
                    previousState.copy(
                        counterState =
                            previousState.counterState.copy(
                                counterValue = previousState.counterState.counterValue * action.by,
                            ),
                    )
                }

                is CounterAction.Reset -> {
                    AppState()
                }

                else -> {
                    previousState
                }
            }
        }
    }
}
