package dev.aptewicz.scopestotherescue.decimalcounter.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dev.aptewicz.scopestotherescue.decimalcounter.domain.DecimalCounterScreenState

class DecimalCounterScreenPreviewProvider : PreviewParameterProvider<DecimalCounterScreenState> {
    private val parameters =
        listOf(
            "10" to DecimalCounterScreenState(counterValue = 10),
            "105" to DecimalCounterScreenState(counterValue = 105),
        )

    override val values: Sequence<DecimalCounterScreenState>
        get() = parameters.map { it.second }.asSequence()

    override fun getDisplayName(index: Int): String? = parameters[index].first
}
