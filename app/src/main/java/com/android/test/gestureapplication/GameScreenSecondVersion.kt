package com.android.test.gestureapplication

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private var selectedLettersList = mutableListOf<String>()

@Composable
fun GameScreenSecondVersion() {
    var selectedWord by remember { mutableStateOf("") }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var width by remember { mutableStateOf(0.dp) }
    var height by remember { mutableStateOf(0.dp) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(6),
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {

            items(letters) { item ->
                Letter(
                    letter = item,
                    modifier = Modifier,
                    selectedWord = selectedWord,
                    onWordSelected = { selectedWord = it },
                    offsetX = offsetX,
                    offsetY = offsetY,
                    width = width.value,
                    height = height.value
                )
            }
        }

        MovableRectangle(
            modifier = Modifier.pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        offsetX = offset.x
                        offsetY = offset.y
                    },
                    onDrag = { change, dragAmount ->
                        width += dragAmount.x.toDp()
                        height += dragAmount.y.toDp()

                        updateSelectedWord(
                            offsetX,
                            offsetY,
                            width.value,
                            height.value,
                            rowItems = 6
                        )
                    },
                    onDragEnd = {
                        height = 0.dp
                        width = 0.dp
                        selectedWord = ""
                    }
                )
            },
            color = colorResource(id = R.color.letter_selector_color),
            width = width.value,
            height = height.value,
            offsetX = offsetX,
            offsetY = offsetY
        )
    }
}

fun updateSelectedWord(offsetX: Float, offsetY: Float, width: Float, height: Float, rowItems: Int) {
    val selectedLetters = mutableListOf<String>()

    val rectLeft = offsetX
    val rectRight = offsetX + width
    val rectTop = offsetY
    val rectBottom = offsetY + height

    letters.forEachIndexed { index, letter ->
        // For each letter calculate the row and column indices of the letter based on its index in the list and the number of culumns in grid

        val row = index / rowItems
        val column = index % rowItems
        val letterLeft = column * 50f
        val letterRight = letterLeft + 50
        val letterTop = row * 50f
        val letterBottom = letterTop + 50


        if (rectLeft < letterRight && rectRight > letterLeft && rectTop < letterBottom && rectBottom > letterTop) {
            selectedLetters.add(letter)
        }
    }
    selectedLettersList = selectedLetters
    Log.e("Selected words: ", "$selectedLettersList")
}

@Composable
fun Letter(
    letter: String,
    modifier: Modifier,
    selectedWord: String,
    onWordSelected: (String) -> Unit,
    offsetX: Float,
    offsetY: Float,
    width: Float,
    height: Float
) {
    val isSelected = selectedWord.contains(letter)
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(if (isSelected) Color.Green.copy(alpha = 0.5f) else Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = letter,
            style = LocalTextStyle.current.merge(
                TextStyle(
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = FontFamily(Font(R.font.titan_one_regular))
                )
            )
        )
        Text(
            text = letter,
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
