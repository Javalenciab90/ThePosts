package com.javalenciab90.platform.effect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

/**
 * Use this approach when you want a simple and concise way to collect flows and handle side effects.
 * Great for most common cases where you're only concerned with collecting and handling a single type of event.
 */

@Composable
fun <T> ObserveEffects(
    flow: Flow<T>,
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    onEffect: suspend (T) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(flow, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(lifecycleState) {
            withContext(Dispatchers.Main.immediate) {
                flow.collect(onEffect)
            }
        }
    }
}