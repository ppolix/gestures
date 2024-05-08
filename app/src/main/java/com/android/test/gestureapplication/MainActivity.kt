package com.android.test.gestureapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val letters = listOf(
    'A', 'B', 'C', 'D', 'E', 'F',
    'G', 'H', 'I', 'J', 'K', 'L',
    'M', 'N', 'O', 'P', 'Q', 'R',
    'S', 'T', 'U', 'V', 'W', 'X',
    'Y', 'Z'
)

data class Item(
    val character: Char,
    val isSelected: Boolean,
    val isFirst: Boolean,
    val color: Color
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleMovingDetector()
//            GestureBox()
//            GameScreenSecondVersion()
//            GameScreenThirdVersion()
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
