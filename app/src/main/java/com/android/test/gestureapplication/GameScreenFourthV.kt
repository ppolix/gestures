package com.android.test.gestureapplication

import android.content.res.Configuration
import android.util.DisplayMetrics
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitDragOrCancellation
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.test.gestureapplication.state.GameState
import com.android.test.gestureapplication.state.GameUiEvent
import com.android.test.gestureapplication.state.listOfChars

@Composable
fun GameScreenFourthV(
    uiEvent: (GameUiEvent) -> Unit,
    state: GameState
) {

    val fiftyPxValue = LocalDensity.current.run { 130.dp.toPx() }
    val onePxVal = LocalDensity.current.run { 1.dp.toPx() }

    val list = remember { mutableStateOf(listOfChars.toList()) }
    val selectedWordsList = mutableListOf<Item>()
    val configuration = LocalConfiguration.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentAlignment = Alignment.Center
    ) {

//        val screenWidthPx = configuration.screenWidthDp.dp.toPx()
//        val screenHeightPx = configuration.screenHeightDp.dp.toPixels()
//        val oneDp = 1.dp.toPixels()

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier,

            verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            itemsIndexed(state.items) { index, item ->

                LetterItem(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .pointerInput(Unit) {
                            awaitEachGesture {

                                waitForUpOrCancellation()?.let {
                                    it.consume()
                                }
                            }
//                            detectDragGestures(
//                                onDragStart = { offset -> },
//                                onDrag = { change, dragAmount ->
//
//                                    val screenW = configuration.screenWidthDp
//                                    val screenWID = DisplayMetrics().widthPixels
//                                    Log.e("SCREEN W1 in px", "${screenW}")
//                                    Log.e("SCREEN W2 in px", "${screenWID.dp}")
//
//                                    val oneItemWidth = screenW / 3
//                                    val draggedIndex =
//                                        findDraggedIndex(
//                                            dragAmount = dragAmount,
//                                            configuration = configuration,
//                                            onePx = 1.dp.toPx(),
//                                            screenWidthPx = configuration.screenWidthDp.dp.value,
//                                            screenHeightPx = configuration.screenHeightDp.dp.toPx(),
//                                        )
//
//                                    uiEvent(GameUiEvent.OnCharSelected(index))
//                                },
//                                onDragEnd = {
//                                    val selectedItems = state.items.filter {
//                                        it.isSelected
//                                    }
//                                    selectedWordsList.addAll(selectedItems)
//                                }
//                            )
                        },
                    item = item,
                    isSelected = item.isSelected
                )
            }
        }
    }
}

@Composable
fun LetterItem(
    modifier: Modifier,
//    onClick: () -> Unit,
    item: Item,
    isSelected: Boolean
) {
    Box(
        modifier = modifier
            .width(50.dp)
            .height(50.dp)
            .background(color = if (isSelected) Color.Magenta else Color.Transparent),
        contentAlignment = Alignment.Center

    ) {
        Text(
            text = item.character.toString(),
            style = LocalTextStyle.current.merge(
                TextStyle(
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = FontFamily(Font(R.font.titan_one_regular))
                )
            )
        )
        Text(
            text = item.character.toString(),
            style = LocalTextStyle.current.merge(
                TextStyle(
                    color = colorResource(id = R.color.border_letter_color),
                    fontSize = 30.sp,
                    drawStyle = Stroke(width = 3f, join = StrokeJoin.Round),
                    fontFamily = FontFamily(Font(R.font.titan_one_regular))
                )
            )
        )
    }
}

private fun findDraggedIndex(
    dragAmount: Offset,
    configuration: Configuration,
    onePx: Float,
    screenWidthPx: Float,
    screenHeightPx: Float
): Int? {
    val columns = 3
    val rows = 1
    val spacing = onePx

    val screenWidth = screenWidthPx
    val screenHeight = screenHeightPx

    Log.e("Screen Width", "$screenWidth")
    Log.e("Screen Height", "$screenHeight")

    val itemWidth = ((screenWidth / columns))
    val itemHeight = screenHeight / rows
    Log.e("ITEM WIDTH", "$itemWidth")

//    val rowIndex = (dragAmount.y / (itemHeight))
    val columnIndex = (dragAmount.x / (itemWidth))

    Log.e("ITEMS COUNT", "$columnIndex")

//    if (rowIndex >= 0 && rowIndex < rows && columnIndex >= 0 && columnIndex < columns) {
//        val index = rowIndex * columns + columnIndex
//        return if (index < listOfChars.size) index else null
//    }

    return null
}

@Composable
fun Dp.toPixels(): Float {
    val density: Float = LocalDensity.current.density
    return this.value * density
}