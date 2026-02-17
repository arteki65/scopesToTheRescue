package dev.aptewicz.scopestotherescue.decimalcounter.ui.scope

interface DecimalCounterScope {
    fun onIncrement(by: Int)

    fun onDecrement(by: Int)

    fun onMultiply(by: Int)

    fun onRandomDecrement()

    fun onRandomIncrement()

    fun onReset()
}
