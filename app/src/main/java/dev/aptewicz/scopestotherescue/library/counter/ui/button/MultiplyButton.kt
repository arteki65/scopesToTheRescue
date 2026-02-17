package dev.aptewicz.scopestotherescue.library.counter.ui.button

import androidx.compose.runtime.Composable
import com.dbschenker.mobile.compose.uicomponents.ui.button.StandardButton

@Composable
fun MultiplyButton(
    multiplyBy: Int,
    onMultiply: (Int) -> Unit,
) {
    StandardButton(label = "x $multiplyBy", onClick = { onMultiply(multiplyBy) })
}
