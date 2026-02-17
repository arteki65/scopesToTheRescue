package dev.aptewicz.scopestotherescue.library.counter.ui.button

import androidx.compose.runtime.Composable
import com.dbschenker.mobile.compose.uicomponents.ui.button.StandardButton

@Composable
fun ResetButton(onReset: () -> Unit) {
    StandardButton(label = "Reset", onClick = onReset)
}
