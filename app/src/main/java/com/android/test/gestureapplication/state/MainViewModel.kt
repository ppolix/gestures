package com.android.test.gestureapplication.state

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    val _state = MutableStateFlow(GameState())
    val state = _state.asStateFlow()

    fun onEvent(uiEvent: GameUiEvent) {
        when (uiEvent) {
            is GameUiEvent.OnCharSelected -> updateSelectedCharState(uiEvent.selectedInde)
        }
    }

    private fun updateSelectedCharState(selectedIndex: Int) {

        val updatedList = _state.value.items.mapIndexed { index, item ->
            if (index == selectedIndex) {
                item.copy(
                    isSelected = !item.isSelected
                )
            } else item
        }
        _state.value = _state.value.copy(items = updatedList)
    }
}