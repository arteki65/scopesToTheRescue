package dev.aptewicz.scopestotherescue.library.counter.ui.button

import androidx.compose.runtime.Composable
import com.dbschenker.mobile.compose.uicomponents.ui.button.StandardButton

@Composable
fun RandomDecrementButton(onRandomDecrement: () -> Unit) {
    StandardButton(label = "- random", onClick = onRandomDecrement)
}
