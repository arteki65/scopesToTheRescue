package dev.aptewicz.scopestotherescue.decimalcounter.ui

import dev.aptewicz.scopestotherescue.decimalcounter.domain.DecimalCounterScreenState
import dev.aptewicz.scopestotherescue.library.store.AppStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DecimalCounterViewModelTest {
    private lateinit var appStore: AppStore

    @BeforeTest
    fun setupTest() {
        AppStore.init()
        appStore = AppStore.instance
    }

    @Test
    fun verifyOnDecrementDecreaseCounterValue() =
        runTest {
            val viewModel = DecimalCounterViewModel(AppStore.instance)

            val emissions = mutableListOf<DecimalCounterScreenState>()
            val job =
                collectState {
                    viewModel.decimalCounterScreenStateFlow.collect { emissions.add(it) }
                }

            viewModel.onDecrement(10)

            assertEquals(2, emissions.size)
            assertEquals(0, emissions[0].counterValue)
            assertEquals(-10, emissions[1].counterValue)

            job.cancel()
        }

    @Test
    fun verifyOnIncrementIncreaseCounterValue() =
        runTest {
            val viewModel = DecimalCounterViewModel(AppStore.instance)

            val emissions = mutableListOf<DecimalCounterScreenState>()
            val job =
                collectState {
                    viewModel.decimalCounterScreenStateFlow.collect { emissions.add(it) }
                }

            viewModel.onIncrement(10)

            assertEquals(2, emissions.size)
            assertEquals(0, emissions[0].counterValue)
            assertEquals(10, emissions[1].counterValue)

            job.cancel()
        }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun TestScope.collectState(block: suspend () -> Unit): Job =
    launch(UnconfinedTestDispatcher()) {
        block()
    }
