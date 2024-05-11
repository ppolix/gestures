package com.android.test.gestureapplication

import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.android.test.gestureapplication.state.GameState

const val STATE_IDLE = 0
const val STATE_DOWN = 1
const val STATE_MOVE = 2
const val STATE_UP = 3
const val BUTTON_DEFAULT = 0

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ScreenGame(
    uiState: GameState
) {

    var state by remember {
        mutableIntStateOf(STATE_IDLE)
    }
    var currentPos by remember {
        mutableStateOf(Offset(0f, 0f))
    }
    val selectedChars = mutableListOf<Item>()

    val itemSize: Dp = 50.dp
    val spacing: Dp = 10.dp
    val offset: Dp = 10.dp
    val rows: Int = 5
    val columns: Int = 5

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        val itemSizePx = with(LocalDensity.current) { itemSize.toPx() }
        val spacingPx = with(LocalDensity.current) { spacing.toPx() }
        val offsetPx = with(LocalDensity.current) { offset.toPx() }
        val itemColor: Color = Color.Blue

        val textMeasurer = rememberTextMeasurer()

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInteropFilter {
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            state = STATE_DOWN
                            currentPos = Offset(it.x, it.y)
                        }
                        MotionEvent.ACTION_MOVE -> {
                            state = STATE_MOVE
                            currentPos = Offset(it.x, it.y)
                        }

                        MotionEvent.ACTION_UP -> {
                            state = STATE_UP

                        }
                    }
                    true
                }
        ) {
            for (row in 0 until rows) {
                for (column in 0 until columns) {
                    val x = column * (itemSizePx + spacingPx)
                    val y = row * (itemSizePx + spacingPx)
//                    val index = row * columns + column

//                    drawRect(
//
//                    )

                    drawText(
                        textMeasurer = textMeasurer,
                        text = uiState.items[0].character.toString(),
                        topLeft = Offset(x + spacingPx, y + spacingPx + itemSizePx / 3),
//                        fontSize = itemSizePx * 0.6f,
//                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenGamePreview() {
    ScreenGame(uiState = GameState())
}