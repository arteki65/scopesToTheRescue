package dev.aptewicz.scopestotherescue.binarycounter.ui

import dev.aptewicz.scopestotherescue.library.counter.ui.CounterScreenState
import dev.aptewicz.scopestotherescue.library.store.AppStore
import dev.aptewicz.scopestotherescue.recordStateEmissions
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class BinaryCounterViewModelTest {
    private lateinit var viewModel: BinaryCounterViewModel
    private lateinit var stateEmissions: MutableList<CounterScreenState>

    @BeforeTest
    fun setupTest() {
        AppStore.init()
        viewModel =
            BinaryCounterViewModel(
                store = AppStore.instance,
            )
        stateEmissions = mutableListOf()
    }

    @Test
    fun verifyOnDecrementDecreaseCounterValue() =
        runTest {
            val job =
                recordStateEmissions(viewModel.counterScreenStateFlow) {
                    stateEmissions.add(it)
                }

            viewModel.onDecrement(10)

            assertEquals(2, stateEmissions.size)
            assertEquals("0", stateEmissions[0].counterValue)
            assertEquals("-1010", stateEmissions[1].counterValue)

            job.cancel()
        }

    @Test
    fun verifyOnIncrementIncreaseCounterValue() =
        runTest {
            val job =
                recordStateEmissions(viewModel.counterScreenStateFlow) {
                    stateEmissions.add(it)
                }

            viewModel.onIncrement(10)

            assertEquals(2, stateEmissions.size)
            assertEquals("0", stateEmissions[0].counterValue)
            assertEquals("1010", stateEmissions[1].counterValue)

            job.cancel()
        }

    @Test
    fun verifyOnResetClearsCounterScreenState() =
        runTest {
            val job =
                recordStateEmissions(viewModel.counterScreenStateFlow) {
                    stateEmissions.add(it)
                }

            viewModel.onIncrement(by = 5)
            viewModel.onReset()

            assertEquals(3, stateEmissions.size)
            // init state
            assertEquals("0", stateEmissions[0].counterValue)
            // incremented by 5
            assertEquals("101", stateEmissions[1].counterValue)
            // reset
            assertEquals("0", stateEmissions[2].counterValue)

            job.cancel()
        }
}
