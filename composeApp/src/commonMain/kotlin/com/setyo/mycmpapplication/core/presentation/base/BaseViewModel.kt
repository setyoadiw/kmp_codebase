package com.setyo.mycmpapplication.core.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<State, Action, Event>(
    initialState: State
) : ViewModel() {

    protected val _effect = MutableSharedFlow<Event>(extraBufferCapacity = 1)
    val effect = _effect.asSharedFlow()

    protected val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state
        .onStart {
            onStart()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            initialState
        )

    protected open fun onStart() {}

    open fun onAction(event: Action) {}

    protected fun setState(reducer: (State) -> State) {
        _state.update(reducer)
    }

    protected suspend fun sendEvent(effect: Event) {
        _effect.emit(effect)
    }


}