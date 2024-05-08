package com.android.test.gestureapplication

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameScreenThirdVersion() {

    val listOfCharacters = listOf(
        Item('A', color = Color.Transparent, isSelected = false, isFirst = true),
        Item('b', color = Color.Transparent, isSelected = false, isFirst = false),
        Item('c', color = Color.Transparent, isSelected = false, isFirst = false),
        Item('d', color = Color.Transparent, isSelected = false, isFirst = false),
        Item('e', color = Color.Transparent, isSelected = false, isFirst = true),
        Item('f', color = Color.Transparent, isSelected = false, isFirst = true),
        Item('j', color = Color.Transparent, isSelected = false, isFirst = true),
        Item('k', color = Color.Transparent, isSelected = false, isFirst = true),
        Item('l', color = Color.Transparent, isSelected = false, isFirst = true)

    )

    val list = remember { mutableStateOf(listOfCharacters.toList()) }
    var selectedWord by remember { mutableStateOf("") }
    val mutableList = list.value.toMutableList()
    val selectedWordsList = mutableListOf<Item>()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentAlignment = Alignment.Center
    ) {

        val oneCell =

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            var updatedItem: Item? = null
            itemsIndexed(list.value) { index, item ->
                LetterItem(
                    letter = item.character,
                    modifier = Modifier,
//                        .pointerInput(Unit) {
//                            detectDragGestures(
//                                onDragStart = { offset ->
//
//                                    updatedItem = item.copy(
//                                        isSelected = !item.isSelected,
//                                        isFirst = !item.isFirst,
//                                        color = Color.Magenta
//                                    )
//
//
//                                    mutableList[index] = requireNotNull(updatedItem)
//                                    list.value = mutableList
//
//                                    Log.e("is selected $item", "")
//
//                                    Log.e("Drag Coordinates", "${offset.x} ${offset.y}")
//                                },
//                                onDrag = { change, dragAmount ->
//
//                                    updatedItem = item.copy(
//                                        isSelected = !item.isSelected,
//                                        color = Color.Magenta
//                                    )
//
//
//                                    mutableList[index] = requireNotNull(updatedItem)
//
//                                    Log.e("is selected $item", "${item.isSelected}")
//                                    Log.e(
//                                        "Drag Coordinates",
//                                        "${change.position.x} ${change.position.y}"
//                                    )
//
//                                },
//                                onDragEnd = {
//                                    selectedWordsList.add(requireNotNull(updatedItem))
////                                    updatedItem = item.copy(
////                                        isSelected = !item.isSelected,
////                                        color = Color.Transparent
////                                    )
//                                    Log.e("END", "${selectedWordsList}")
//                                }
//                            )
//                        },
                    selectedWord = selectedWord,
                    isSelected = item.isSelected,
                    color = if (item.isSelected) Color.Magenta else Color.Transparent,
                    item = item,
                    onWordSelected = { selectedWord = it },
                    onDragStart = { offset ->
//                        updatedItem = item.copy(
////                            isSelected = !item.isSelected,
//                            isFirst = !item.isFirst,
////                            color = Color.Magenta
//                        )

                        mutableList[index] = requireNotNull(updatedItem)
                        list.value = mutableList

                        Log.e("is selected $item", "")

                        Log.e("Drag Coordinates", "${offset.x} ${offset.y}")
                    },
                    onDrag = { change, dragAmount ->
                        updatedItem = item.copy(
                            isSelected = !item.isSelected,
                            color = Color.Magenta
                        )

                        mutableList[index] = requireNotNull(updatedItem)

                        Log.e(
                            "Drag Coordinates",
                            "${change.position.x} ${change.position.y}"
                        )
                    },
                    onDragEnd = {
                        selectedWordsList.add(requireNotNull(updatedItem))
                        updatedItem = item.copy(
                            isSelected = !item.isSelected,
                            color = Color.Transparent
                        )
                        Log.e("END", "${selectedWordsList}")
                    }
                )
            }
        }
    }
}

@Composable
fun LetterItem(
    letter: Char,
    color: Color,
    modifier: Modifier,
    item: Item,
    onDragStart: (Offset) -> Unit,
    onDrag: (PointerInputChange, Offset) -> Unit,
    onDragEnd: () -> Unit,
    selectedWord: String,
    onWordSelected: (String) -> Unit,
    isSelected: Boolean
) {
    Box(
        modifier = modifier
            .size(50.dp)
            .background(color = color)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset -> onDragStart(offset) },
                    onDrag = { change, dragAmount ->
                        onDrag(change, dragAmount)
                    },
                    onDragEnd = onDragEnd
                )
            },
        contentAlignment = Alignment.Center
    ) {
        ////            .background(if (isSelected) Color.Magenta else Color.Transparent),
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


