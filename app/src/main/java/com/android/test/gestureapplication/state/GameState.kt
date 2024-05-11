package com.android.test.gestureapplication.state

import com.android.test.gestureapplication.Item

data class GameState(
    val items: List<Item> = listOfChars
)


val listOfChars = listOf(
    Item(1, 'A', isSelected = false, isFirst = false),
    Item(2, 'b', isSelected = false, isFirst = false),
    Item(3, 'c', isSelected = false, isFirst = false),
    Item(4, 'd', isSelected = false, isFirst = false),
    Item(5, 'e', isSelected = false, isFirst = false),
    Item(6, 'f', isSelected = false, isFirst = false),
    Item(7, 'j', isSelected = false, isFirst = false),
    Item(11, 'k', isSelected = false, isFirst = false),
    Item(13, 'l', isSelected = false, isFirst = false)
)

