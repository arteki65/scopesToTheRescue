package dev.aptewicz.scopestotherescue.decimalcounter.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dbschenker.mobile.compose.uicomponents.preview.DefaultPreview
import com.dbschenker.mobile.compose.uicomponents.theme.spacing
import dev.aptewicz.scopestotherescue.app.theme.ScopesToTheRescueTheme
import dev.aptewicz.scopestotherescue.decimalcounter.ui.preview.DecimalCounterScreenPreviewProvider
import dev.aptewicz.scopestotherescue.decimalcounter.ui.scope.DecimalCounterScope
import dev.aptewicz.scopestotherescue.library.counter.ui.CounterDisplay
import dev.aptewicz.scopestotherescue.library.counter.ui.CounterScreenState
import dev.aptewicz.scopestotherescue.library.counter.ui.OperationButtonsRow
import dev.aptewicz.scopestotherescue.library.counter.ui.button.DecrementButton
import dev.aptewicz.scopestotherescue.library.counter.ui.button.IncrementButton
import dev.aptewicz.scopestotherescue.library.counter.ui.button.MultiplyButton
import dev.aptewicz.scopestotherescue.library.counter.ui.button.RandomDecrementButton
import dev.aptewicz.scopestotherescue.library.counter.ui.button.RandomIncrementButton
import dev.aptewicz.scopestotherescue.library.preview.previewScope
import dev.aptewicz.scopestotherescue.library.random.RandomGeneratorImpl
import dev.aptewicz.scopestotherescue.library.store.AppStore

@Composable
fun DecimalCounterScreen() {
    val viewModel: DecimalCounterViewModel =
        viewModel {
            DecimalCounterViewModel(
                store = AppStore.instance,
                randomGenerator = RandomGeneratorImpl(),
            )
        }
    val state by viewModel.counterScreenStateFlow.collectAsStateWithLifecycle(
        CounterScreenState(),
    )
    viewModel.DecimalCounterScreenContent(
        state,
    )

    DisposableEffect(Unit) {
        onDispose { viewModel.onReset() }
    }
}

@Composable
fun DecimalCounterScope.DecimalCounterScreenContent(state: CounterScreenState) {
    ScopesToTheRescueTheme {
        Scaffold(content = { innerPadding ->
            Column(
                modifier =
                    Modifier
                        .padding(innerPadding)
                        .padding(horizontal = MaterialTheme.spacing.medium)
                        .fillMaxSize(),
            ) {
                CounterDisplay(state)
                OperationButtonsRow {
                    IncrementButton(incrementBy = 1, onIncrement = ::onIncrement)
                    DecrementButton(decrementBy = 1, onDecrement = ::onDecrement)
                    IncrementButton(incrementBy = 10, onIncrement = ::onIncrement)
                    DecrementButton(decrementBy = 10, onDecrement = ::onDecrement)
                    MultiplyButton(multiplyBy = 2, onMultiply = ::onMultiply)
                    RandomIncrementButton(onRandomIncrement = ::onRandomIncrement)
                    RandomDecrementButton(onRandomDecrement = ::onRandomDecrement)
                }
            }
        })
    }
}

@DefaultPreview
@Composable
fun DecimalCounterScreenPreview(
    @PreviewParameter(DecimalCounterScreenPreviewProvider::class) state: CounterScreenState,
) {
    previewScope<DecimalCounterScope>().DecimalCounterScreenContent(state)
}
