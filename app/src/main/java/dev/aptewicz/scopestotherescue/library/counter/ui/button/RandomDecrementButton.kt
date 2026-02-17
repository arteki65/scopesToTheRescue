package dev.aptewicz.scopestotherescue.library.counter.ui.button

import androidx.compose.runtime.Composable
import com.dbschenker.mobile.compose.uicomponents.ui.button.StandardButton
import dev.aptewicz.scopestotherescue.library.counter.ui.scope.RandomDecrementScope

@Composable
fun RandomDecrementScope.RandomDecrementButton() {
    StandardButton(label = "- random", onClick = ::onRandomDecrement)
}
