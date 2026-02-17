package dev.aptewicz.scopestotherescue.decimalcounter.ui

import dev.aptewicz.scopestotherescue.library.counter.domain.CounterState
import dev.aptewicz.scopestotherescue.library.random.RandomGenerator
import dev.aptewicz.scopestotherescue.library.store.AppStore
import io.mockk.every
import io.mockk.mockk
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
    private val randomGeneratorMock: RandomGenerator = mockk(relaxed = true)
    private lateinit var viewModel: DecimalCounterViewModel

    @BeforeTest
    fun setupTest() {
        AppStore.init()
        viewModel =
            DecimalCounterViewModel(
                store = AppStore.instance,
                randomGenerator = randomGeneratorMock,
            )
    }

    @Test
    fun verifyOnDecrementDecreaseCounterValue() =
        runTest {
            val stateEmissions = mutableListOf<CounterState>()
            val job =
                collectState {
                    viewModel.counterScreenStateFlow.collect { stateEmissions.add(it) }
                }

            viewModel.onDecrement(10)

            assertEquals(2, stateEmissions.size)
            assertEquals(0, stateEmissions[0].counterValue)
            assertEquals(-10, stateEmissions[1].counterValue)

            job.cancel()
        }

    @Test
    fun verifyOnIncrementIncreaseCounterValue() =
        runTest {
            val stateEmissions = mutableListOf<CounterState>()
            val job =
                collectState {
                    viewModel.counterScreenStateFlow.collect { stateEmissions.add(it) }
                }

            viewModel.onIncrement(10)

            assertEquals(2, stateEmissions.size)
            assertEquals(0, stateEmissions[0].counterValue)
            assertEquals(10, stateEmissions[1].counterValue)

            job.cancel()
        }

    @Test
    fun verifyOnRandomIncrementIncreaseCounterValueByRandomlyGeneratedValue() =
        runTest {
            every { randomGeneratorMock.nextInt() } returns 33

            val stateEmissions = mutableListOf<CounterState>()
            val job =
                collectState {
                    viewModel.counterScreenStateFlow.collect { stateEmissions.add(it) }
                }

            viewModel.onRandomIncrement()

            assertEquals(2, stateEmissions.size)
            // init state
            assertEquals(0, stateEmissions[0].counterValue)
            // incremented by random (33)
            assertEquals(33, stateEmissions[1].counterValue)

            job.cancel()
        }

    @Test
    fun verifyOnRandomDecrementDecreaseCounterValueByRandomlyGeneratedValue() =
        runTest {
            every { randomGeneratorMock.nextInt() } returns 13

            val stateEmissions = mutableListOf<CounterState>()
            val job =
                collectState {
                    viewModel.counterScreenStateFlow.collect { stateEmissions.add(it) }
                }

            viewModel.onRandomDecrement()

            assertEquals(2, stateEmissions.size)
            // init state
            assertEquals(0, stateEmissions[0].counterValue)
            // decremented by random (13)
            assertEquals(-13, stateEmissions[1].counterValue)

            job.cancel()
        }

    @Test
    fun verifyOnMultiplyIncreaseCounterValue() =
        runTest {
            every { randomGeneratorMock.nextInt() } returns 13

            val stateEmissions = mutableListOf<CounterState>()
            val job =
                collectState {
                    viewModel.counterScreenStateFlow.collect { stateEmissions.add(it) }
                }

            viewModel.onIncrement(by = 5)
            viewModel.onMultiply(by = 5)

            assertEquals(3, stateEmissions.size)
            // init state
            assertEquals(0, stateEmissions[0].counterValue)
            // incremented by 5
            assertEquals(5, stateEmissions[1].counterValue)
            // multiplied by 5
            assertEquals(25, stateEmissions[2].counterValue)

            job.cancel()
        }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun TestScope.collectState(block: suspend () -> Unit): Job =
    launch(UnconfinedTestDispatcher()) {
        block()
    }
