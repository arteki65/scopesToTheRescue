package dev.aptewicz.scopestotherescue

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
fun <State> TestScope.recordStateEmissions(
    flow: Flow<State>,
    recordingBlock: (State) -> Unit,
): Job =
    launch(UnconfinedTestDispatcher()) {
        flow.collect { recordingBlock(it) }
    }
