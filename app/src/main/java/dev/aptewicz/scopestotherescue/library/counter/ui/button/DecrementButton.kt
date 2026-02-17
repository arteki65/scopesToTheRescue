package dev.aptewicz.scopestotherescue.library.counter.ui.button

import androidx.compose.runtime.Composable
import com.dbschenker.mobile.compose.uicomponents.ui.button.StandardButton
import dev.aptewicz.scopestotherescue.library.counter.ui.scope.DecrementScope

@Composable
fun DecrementScope.DecrementButton(decrementBy: Int) {
    StandardButton(label = "- $decrementBy", onClick = { onDecrement(decrementBy) })
}
