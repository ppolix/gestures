package com.android.test.gestureapplication

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GestureBox() {
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
                GameScreenLetterItem(
                    letter = item,
                    modifier = Modifier,
                    selectedWord = selectedWord,
                    onWordSelected = { selectedWord = it }
                )
            }
        }

        MovableRectangle(
            modifier = Modifier
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = { offset ->
                            offsetX = offset.x
                            offsetY = offset.y
                        },
                        onDrag = { change, dragAmount ->
                            width += dragAmount.x.toDp()
                            height += dragAmount.y.toDp()
                        },
                        onDragEnd = {
                            height = 0.dp
                            width = 0.dp
                        }
                    )
                },
            color = colorResource(id = R.color.letter_selector_color),
            width = width.value,
            height = height.value,
            offsetX = offsetX,
            offsetY = offsetY,
        )
    }
}

@Composable
fun GameScreenLetterItem(
    letter: Char,
    modifier: Modifier,
    selectedWord: String,
    onWordSelected: (String) -> Unit
) {
    Box(
        modifier = Modifier.size(50.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = letter.toString(),
            style = LocalTextStyle.current.merge(
                TextStyle(
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = FontFamily(Font(R.font.titan_one_regular))
                )
            )
        )
        Text(
            text = letter.toString(),
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

@Composable
fun MovableRectangle(
    color: Color,
    modifier: Modifier,
    width: Float,
    height: Float,
    offsetX: Float,
    offsetY: Float
) {
    Canvas(
        modifier = modifier
            .alpha(0.5f)
            .fillMaxSize()
            .animateContentSize(
                animationSpec = spring(
                    stiffness = Spring.DampingRatioNoBouncy
                )
            )
    ) {
        drawRoundRect(
            color = color,
            size = Size(
                width = width,
                height = height
            ),
            topLeft = Offset(offsetX, offsetY),
            cornerRadius = CornerRadius(7f)
        )
    }
}

@Preview(showSystemUi = false, showBackground = true)
@Composable
fun LettersGridPreview() {
    MovableRectangle(
        color = colorResource(id = R.color.letter_selector_color),
        width = 150F,
        height = 25F,
        modifier = Modifier,
        offsetX = 1f, offsetY = 2f
    )
}
