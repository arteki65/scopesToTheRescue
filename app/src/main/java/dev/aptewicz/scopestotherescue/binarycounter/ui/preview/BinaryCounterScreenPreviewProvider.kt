package dev.aptewicz.scopestotherescue.binarycounter.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dev.aptewicz.scopestotherescue.library.counter.ui.CounterScreenState

class BinaryCounterScreenPreviewProvider : PreviewParameterProvider<CounterScreenState> {
    private val parameters =
        listOf(
            "10" to CounterScreenState(counterValue = "1010"),
            "105" to CounterScreenState(counterValue = "1101001"),
        )

    override val values: Sequence<CounterScreenState>
        get() = parameters.map { it.second }.asSequence()

    override fun getDisplayName(index: Int): String? = parameters[index].first
}
