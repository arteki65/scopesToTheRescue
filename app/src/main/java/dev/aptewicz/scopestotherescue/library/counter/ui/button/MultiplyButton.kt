package dev.aptewicz.scopestotherescue.library.counter.ui.button

import androidx.compose.runtime.Composable
import com.dbschenker.mobile.compose.uicomponents.ui.button.StandardButton
import dev.aptewicz.scopestotherescue.library.counter.ui.scope.MultiplyScope

@Composable
fun MultiplyScope.MultiplyButton(multiplyBy: Int) {
    StandardButton(label = "x $multiplyBy", onClick = { onMultiply(multiplyBy) })
}
