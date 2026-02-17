package dev.aptewicz.scopestotherescue.library.counter.ui.button

import androidx.compose.runtime.Composable
import com.dbschenker.mobile.compose.uicomponents.ui.button.StandardButton
import dev.aptewicz.scopestotherescue.library.counter.ui.scope.IncrementScope

@Composable
fun IncrementScope.IncrementButton(incrementBy: Int) {
    StandardButton(label = "+ $incrementBy", onClick = { onIncrement(incrementBy) })
}
