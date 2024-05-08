package com.android.test.gestureapplication

import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Float.toDp(): Dp {
    return (this / LocalDensity.current.density).dp
}


@Composable
fun SimpleMovingDetector() {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val columns = 4
        val rows = (letters.size + columns - 1) / columns

        var isSelected by remember {
            mutableStateOf(false)
        }

        LazyVerticalGrid(
            modifier = Modifier.pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {

                    },
                    onDrag = { change, dragAmount ->
                        val itemsOfCells = dragAmount.x.toDp()
                        Log.e("WIDTH OF ONE ITEM", "")
                        Log.e("DISTANCE", "${itemsOfCells}")
                        val distance = dragAmount
                    },
//                        val oneItemSize =
//                            isSelected = if (dragAmount.x.toDp() / 150.dp)
//                    },
                    onDragEnd = {}
                )
            },
            columns = GridCells.Fixed(6)
        ) {
            items(items = letters, key = { it.toString() }) { letter ->
                val index = letters.indexOf(letter)
                Character(
                    letter = letter,
                    index = index,
                    gridSize = IntSize(columns, rows),
                    isSelected = isSelected
                )
            }
        }
    }
}

@Composable
fun Character(
    letter: Char,
    index: Int,
    isSelected: Boolean,
    gridSize: IntSize
) {
    var offsetState by remember {
        mutableStateOf(Offset(0f, 0f))
    }
    val alpha by animateFloatAsState(
        targetValue = if (isSelected) 1f else 0.5f,
        animationSpec = spring(stiffness = Spring.StiffnessVeryLow)
    )

    val density = LocalDensity.current.density

    Box(
        modifier = Modifier
            .alpha(alpha)
            .background(Color.Magenta)
            .width(100.dp)
            .height(100.dp)
            .background(if (isSelected) Color.Magenta else Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = letter.toString(),
            style = LocalTextStyle.current.merge(
                TextStyle(
                    color = Color.Black,
                    fontSize = 30.sp,
                    fontFamily = FontFamily(Font(R.font.titan_one_regular))
                )
            )
        )
    }
}