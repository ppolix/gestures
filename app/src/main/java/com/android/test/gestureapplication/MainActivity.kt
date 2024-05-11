package com.android.test.gestureapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.android.test.gestureapplication.state.MainViewModel

val letters = listOf(
    'A', 'B', 'C', 'D', 'E', 'F',
    'G', 'H', 'I', 'J', 'K', 'L',
    'M', 'N', 'O', 'P', 'Q', 'R',
    'S', 'T', 'U', 'V', 'W', 'X',
    'Y', 'Z'
)

data class Item(
    val id: Int,
    val character: Char,
    val isSelected: Boolean,
    val isFirst: Boolean,
)

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state by viewModel.state.collectAsState()
//            GameScreenSolutionNew(state, viewModel::onEvent)
            PhotosGrid()
        }
    }

    @Composable
    fun GameLettersGrid() {
        GestureBox()
    }
}

@Composable
fun CustomLazyGrid() {

}
