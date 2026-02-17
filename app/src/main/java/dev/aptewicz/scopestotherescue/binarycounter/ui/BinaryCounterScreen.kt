package dev.aptewicz.scopestotherescue.binarycounter.ui

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
import dev.aptewicz.scopestotherescue.binarycounter.ui.preview.BinaryCounterScreenPreviewProvider
import dev.aptewicz.scopestotherescue.library.counter.ui.CounterDisplay
import dev.aptewicz.scopestotherescue.library.counter.ui.CounterScreenState
import dev.aptewicz.scopestotherescue.library.counter.ui.OperationButtonsRow
import dev.aptewicz.scopestotherescue.library.counter.ui.button.DecrementButton
import dev.aptewicz.scopestotherescue.library.counter.ui.button.IncrementButton
import dev.aptewicz.scopestotherescue.library.store.AppStore

@Composable
fun BinaryCounterScreen() {
    val viewModel: BinaryCounterViewModel =
        viewModel {
            BinaryCounterViewModel(
                store = AppStore.instance,
            )
        }
    val state by viewModel.counterScreenStateFlow.collectAsStateWithLifecycle(
        CounterScreenState(),
    )

    BinaryCounterScreenContent(
        state = state,
        onIncrement = viewModel::onIncrement,
        onDecrement = viewModel::onDecrement,
    )

    DisposableEffect(Unit) {
        onDispose { viewModel.onReset() }
    }
}

@Composable
fun BinaryCounterScreenContent(
    state: CounterScreenState,
    onIncrement: (Int) -> Unit = {},
    onDecrement: (Int) -> Unit = {},
) {
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
                    IncrementButton(incrementBy = 1, onIncrement = onIncrement)
                    DecrementButton(decrementBy = 1, onDecrement = onDecrement)
                }
            }
        })
    }
}

@DefaultPreview
@Composable
fun DecimalCounterScreenPreview(
    @PreviewParameter(BinaryCounterScreenPreviewProvider::class) state: CounterScreenState,
) {
    BinaryCounterScreenContent(state)
}
