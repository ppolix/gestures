package com.android.test.gestureapplication.state

sealed class GameUiEvent {
    data class OnCharSelected(val selectedInde: Int) : GameUiEvent()
}