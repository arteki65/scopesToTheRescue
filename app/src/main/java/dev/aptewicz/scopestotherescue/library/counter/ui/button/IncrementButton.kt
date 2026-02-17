package dev.aptewicz.scopestotherescue.library.counter.ui.button

import androidx.compose.runtime.Composable
import com.dbschenker.mobile.compose.uicomponents.ui.button.StandardButton

@Composable
fun IncrementButton(
    incrementBy: Int,
    onIncrement: (Int) -> Unit,
) {
    StandardButton(label = "+ $incrementBy", onClick = { onIncrement(incrementBy) })
}
