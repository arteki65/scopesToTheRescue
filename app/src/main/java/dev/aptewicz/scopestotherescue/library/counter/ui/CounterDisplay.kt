package dev.aptewicz.scopestotherescue.library.counter.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.dbschenker.mobile.compose.uicomponents.theme.spacing
import dev.aptewicz.scopestotherescue.R

@Composable
fun ColumnScope.CounterDisplay(state: CounterScreenState) {
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
            text = state.counterValue,
            style = MaterialTheme.typography.displayMedium,
        )
    }
}
