package dev.aptewicz.scopestotherescue.library.counter.ui.button

import androidx.compose.runtime.Composable
import com.dbschenker.mobile.compose.uicomponents.ui.button.StandardButton

@Composable
fun DecrementButton(
    decrementBy: Int,
    onDecrement: (Int) -> Unit,
) {
    StandardButton(label = "- $decrementBy", onClick = { onDecrement(decrementBy) })
}
