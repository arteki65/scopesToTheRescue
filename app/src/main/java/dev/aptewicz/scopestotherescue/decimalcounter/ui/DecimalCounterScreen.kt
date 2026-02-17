package dev.aptewicz.scopestotherescue.decimalcounter.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
                FlowRow(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = MaterialTheme.spacing.medium),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                ) {
                    StandardButton("+ 1", onClick = { onIncrement(1) })
                    StandardButton("- 1", onClick = { onDecrement(1) })
                    StandardButton("x 2", onClick = { onMultiply(2) })
                    StandardButton("+ random", onClick = { onRandomIncrement() })
                    StandardButton("- random", onClick = { onRandomDecrement() })
                }
            }
        })
    }
}

@DefaultPreview
@Composable
fun DecimalCounterScreenPreview(
    @PreviewParameter(DecimalCounterScreenPreviewProvider::class) state: DecimalCounterScreenState,
) {
    DecimalCounterScreenContent(state)
}
