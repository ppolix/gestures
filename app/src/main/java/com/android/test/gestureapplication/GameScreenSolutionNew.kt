package com.android.test.gestureapplication

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toIntRect
import com.android.test.gestureapplication.state.GameState
import com.android.test.gestureapplication.state.GameUiEvent

@Composable
fun GameScreenSolutionNew(
    uiState: GameState,
    uiEvent: (GameUiEvent) -> Unit
) {

    val state = rememberLazyGridState()
    val selectedIdSet = rememberSaveable {
        mutableStateOf(emptySet<Int>())
    }
    val inSelectionMode by remember { derivedStateOf { selectedIdSet.value.isNotEmpty() } }

    LazyVerticalGrid(
        state = state,
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .draggable(
                state = rememberDraggableState { 0.dp },
                orientation = Orientation.Vertical,
                onDragStarted = {},
                onDragStopped = {},
            )
            .draggable(
                orientation = Orientation.Horizontal,
                state = rememberDraggableState { 0.dp },
                onDragStarted = {

                },
            ),
//            .charactersDragHandler(
//            lazyGridState = state,
//            selectedIdSet = selectedIdSet,
//            context = LocalContext.current
//        ),
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        items(
            uiState.items,
            key = { it.id }) { item ->

            val selected by remember { derivedStateOf { selectedIdSet.value.contains(item.id) } }

            LetterItem(
                modifier = Modifier,
//                    .semantics {
//                        if (!inSelectionMode) {
//                            onLongClick("Select") {
//                                selectedIdSet.value += item.id
//                                true
//                            }
//                        }
//                    }
//                    .then(
//                        if (inSelectionMode) {
//                            Modifier.toggleable(
//                                value = item.isSelected,
//                                indication = null,
//                                interactionSource = remember { MutableInteractionSource() },
//                                onValueChange = {
//                                    if (it) {
//                                        selectedIdSet.value += item.id
//                                    } else {
//                                        selectedIdSet.value -= item.id
//                                    }
//                                }
//                            )
//                        } else Modifier
//                    ),
//                    .clickable {
//                    uiEvent(GameUiEvent.OnCharSelected(index))
//                    selectedIdSet.value = if (!item.isSelected) {
//                        selectedIdSet.value.plus(index)
//                    } else {
//                        selectedIdSet.value.minus(index)
//                    }
//                    Log.e("SELECTED ITEMS SET", "${selectedIdSet.value}")
//                },
                item = item,
                isSelected = selected,
                inSelectionMode = inSelectionMode
            )
        }
    }
}


@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.charactersDragHandler(
    lazyGridState: LazyGridState,
    selectedIdSet: MutableState<Set<Int>>,
    context: Context
) = pointerInput(Unit) {

    fun LazyGridState.gridItemKeyAtPosition(hitPoint: Offset): Int? =
        layoutInfo.visibleItemsInfo.find { itemInfo ->
            itemInfo.size.toIntRect().contains(hitPoint.round() - itemInfo.offset)
        }?.key as? Int

    var initialKey: Int? = null
    var currentKey: Int? = null

    detectDragGestures(
        onDragStart = { offset ->
            lazyGridState.gridItemKeyAtPosition(offset)?.let { key ->
                if (!selectedIdSet.value.contains(key)) {
                    initialKey = key
                    currentKey = key
                    selectedIdSet.value += key
                }
            }
        },
        onDrag = { change, dragAmount ->
            val isLeftDrag = dragAmount.x < 0
            val isRightDrag = dragAmount.x > 0
            val isUpDrag = dragAmount.y < 0
            val isDownDrag = dragAmount.y > 0

            var minWidthDistance = 40
            val minDistanceRatio = 0.1f

            if (isLeftDrag) {
                Log.e("IS LEFT DRUGGING", "")
            } else if (isRightDrag) {
                Log.e("IS RIGHT DRUGGING", "")
            } else if (isDownDrag) {
                Log.e("IS DOWN DRUGGING", "")
            } else if (isUpDrag) {
                Log.e("IS UP DRUGGING", "")
            }
            if (initialKey != null) {
                lazyGridState.gridItemKeyAtPosition(change.position)?.let { key ->
                    val item = lazyGridState.layoutInfo.visibleItemsInfo.find { it.key == key }

                    Log.e("WIDTH:", "${minWidthDistance}")

                    if (isLeftDrag && isRightDrag) {
                        if (currentKey != key) {
                            Log.e("FROM INITIAL TO CURRENT", "${initialKey!!..currentKey!!}")
                            Log.e("FROM CURRENT TO INITIAL", "${currentKey!!..initialKey!!}")

                            selectedIdSet.value = selectedIdSet.value
//                            .minus(initialKey!!..currentKey!!)
//                            .minus(currentKey!!..initialKey!!)
//                            .plus(initialKey!!..key)
//                            .plus(key..initialKey!!)
                            currentKey = key
                        }
//                    if (selectedIdSet.value.contains(key)) {
//                        if (abs(dragAmount.x) < minWidthDistance) {
//                            selectedIdSet.value = selectedIdSet.value.minus(key)
//                            Log.e("DRAG AMOUNT WIDTH", "${abs(dragAmount.x)}")
//                        }
//                    } else {
//                        selectedIdSet.value = selectedIdSet.value.plus(key)
//                    }
//                    currentKey = key
                    }
                    Log.e("Selected Items", "${selectedIdSet.value}")
                }
            }
        },
        onDragCancel = {
            initialKey = null
        },
        onDragEnd = {
            initialKey = null
        }
    )
}


@Composable
fun LetterItem(
    modifier: Modifier,
//    onClick: () -> Unit,
    inSelectionMode: Boolean,
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