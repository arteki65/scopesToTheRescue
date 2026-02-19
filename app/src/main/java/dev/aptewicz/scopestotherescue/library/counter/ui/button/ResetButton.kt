package dev.aptewicz.scopestotherescue.library.counter.ui.button

import androidx.compose.runtime.Composable
import com.dbschenker.mobile.compose.uicomponents.ui.button.StandardButton
import dev.aptewicz.scopestotherescue.library.counter.ui.scope.ResetScope

@Composable
fun ResetButtonWithCallback(onReset: () -> Unit) {
    StandardButton(label = "Reset", onClick = onReset)
}

@Composable
fun ResetScope.ResetButton() {
    StandardButton(label = "Reset", onClick = ::onReset)
}
