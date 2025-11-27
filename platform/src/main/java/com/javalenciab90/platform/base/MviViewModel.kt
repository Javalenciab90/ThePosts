package com.javalenciab90.platform.base

import android.util.Log
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class MviViewModel<UiState, Intent, Effect>(
    private val coroutineContext: CoroutineContextProvider
) : ViewModel() {

    private val handler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    @CallSuper
    open fun handleError(exception: Throwable) {
        Log.e("ERROR", exception.toString())
    }

    private val mutableUiState by lazy { MutableStateFlow(initialState()) }
    val uiState: StateFlow<UiState> by lazy { mutableUiState.asStateFlow() }

    private val channelEffects: Channel<Effect> = Channel()
    val effects: Flow<Effect> = channelEffects.receiveAsFlow()

    protected val currentUiState: UiState get() = uiState.value

    fun updateState(reducer: UiState.() -> UiState) {
        val newState = uiState.value.reducer()
        mutableUiState.value = newState
    }

    fun sendEffect(effect: Effect) {
        viewModelScope.launch {
            channelEffects.send(effect)
        }
    }

    fun launch(
        coroutineContext: CoroutineContext,
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(handler) {
        withContext(coroutineContext) {
            block()
        }
    }

    abstract fun initialState(): UiState

    abstract fun handleIntent(intent: Intent)
}