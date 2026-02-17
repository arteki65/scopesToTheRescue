package dev.aptewicz.scopestotherescue.decimalcounter.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dbschenker.mobile.compose.uicomponents.preview.DefaultPreview
import com.dbschenker.mobile.compose.uicomponents.theme.spacing
import com.dbschenker.mobile.compose.uicomponents.ui.button.StandardButton
import dev.aptewicz.scopestotherescue.R
import dev.aptewicz.scopestotherescue.app.theme.ScopesToTheRescueTheme
import dev.aptewicz.scopestotherescue.decimalcounter.domain.DecimalCounterScreenState
import dev.aptewicz.scopestotherescue.decimalcounter.ui.preview.DecimalCounterScreenPreviewProvider
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
    val state by viewModel.decimalCounterScreenStateFlow.collectAsStateWithLifecycle(
        DecimalCounterScreenState(),
    )
    DecimalCounterScreenContent(
        state,
        onIncrement = viewModel::onIncrement,
        onDecrement = viewModel::onDecrement,
        onMultiply = viewModel::onMultiply,
        onRandomDecrement = viewModel::onRandomDecrement,
        onRandomIncrement = viewModel::onRandomIncrement,
    )
}

@Composable
fun DecimalCounterScreenContent(
    state: DecimalCounterScreenState,
    onIncrement: (Int) -> Unit = {},
    onDecrement: (Int) -> Unit = {},
    onMultiply: (Int) -> Unit = {},
    onRandomIncrement: () -> Unit = {},
    onRandomDecrement: () -> Unit = {},
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
                OperationButtons(
                    onIncrement = onIncrement,
                    onDecrement = onDecrement,
                    onMultiply = onMultiply,
                    onRandomIncrement = onRandomIncrement,
                    onRandomDecrement = onRandomDecrement,
                )
            }
        })
    }
}

@Composable
private fun OperationButtons(
    onIncrement: (Int) -> Unit,
    onDecrement: (Int) -> Unit,
    onMultiply: (Int) -> Unit,
    onRandomIncrement: () -> Unit,
    onRandomDecrement: () -> Unit,
) {
    FlowRow(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.spacing.medium),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
    ) {
        IncrementButton(incrementBy = 1, onIncrement = onIncrement)
        DecrementButton(decrementBy = 1, onDecrement = onDecrement)
        IncrementButton(incrementBy = 10, onIncrement = onIncrement)
        DecrementButton(decrementBy = 10, onDecrement = onDecrement)
        MultiplyButton(multiplyBy = 2, onMultiply = onMultiply)
        RandomIncrementButton(onRandomIncrement = onRandomIncrement)
        RandomDecrementButton(onRandomDecrement = onRandomDecrement)
    }
}

@Composable
private fun IncrementButton(
    incrementBy: Int,
    onIncrement: (Int) -> Unit,
) {
    StandardButton(label = "+ $incrementBy", onClick = { onIncrement(incrementBy) })
}

@Composable
private fun DecrementButton(
    decrementBy: Int,
    onDecrement: (Int) -> Unit,
) {
    StandardButton(label = "- $decrementBy", onClick = { onDecrement(decrementBy) })
}

@Composable
private fun MultiplyButton(
    multiplyBy: Int,
    onMultiply: (Int) -> Unit,
) {
    StandardButton(label = "x $multiplyBy", onClick = { onMultiply(multiplyBy) })
}

@Composable
private fun RandomIncrementButton(onRandomIncrement: () -> Unit) {
    StandardButton(label = "+ random", onClick = onRandomIncrement)
}

@Composable
private fun RandomDecrementButton(onRandomDecrement: () -> Unit) {
    StandardButton(label = "- random", onClick = onRandomDecrement)
}

@Composable
private fun ColumnScope.CounterDisplay(state: DecimalCounterScreenState) {
    Column(
        modifier =
            Modifier
                .weight(1f)
                .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(id = R.string.counter_value),
            style = MaterialTheme.typography.displaySmall,
        )
        Spacer(Modifier.height(MaterialTheme.spacing.small))
        Text(
            text = "${state.counterValue}",
            style = MaterialTheme.typography.displayMedium,
        )
    }
}

@DefaultPreview
@Composable
fun DecimalCounterScreenPreview(
    @PreviewParameter(DecimalCounterScreenPreviewProvider::class) state: DecimalCounterScreenState,
) {
    DecimalCounterScreenContent(state)
}
