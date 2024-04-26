package com.android.test.gestureapplication

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun AnimatedGesture() {
    var size by remember { mutableStateOf(0.dp) }
    var width by remember { mutableStateOf(10.dp) }
    var height by remember { mutableStateOf(50.dp) }

    var offsetX by remember {
        mutableFloatStateOf(0f)
    }
    var offsetY by remember {
        mutableFloatStateOf(0f)
    }

    Box(
        modifier = Modifier
            .size(width = width, height = height)
            .background(Color.Blue)
            .offset { IntOffset(x = offsetX.roundToInt(), y = offsetY.roundToInt()) }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change: PointerInputChange, dragAmount: Offset ->
                            offsetX += dragAmount.x
                            offsetY += dragAmount.y
                        width += dragAmount.x.toDp()
                        height += dragAmount.y.toDp()
                        Log.e("DRAGGING", "DRAGGINS START")
                    },

                    onDragEnd = {
                        Log.e("DRAGGING", "DRAGGING END")
                    }
                )
            }
    )
}