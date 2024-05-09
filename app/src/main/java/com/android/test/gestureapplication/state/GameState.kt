package com.android.test.gestureapplication.state

import com.android.test.gestureapplication.Item

data class GameState(
    val items: List<Item> = listOfChars
)


val listOfChars = listOf(
    Item('A', isSelected = false, isFirst = false),
    Item('b', isSelected = false, isFirst = false),
    Item('c', isSelected = false, isFirst = false),
    Item('d', isSelected = false, isFirst = false),
    Item('e', isSelected = false, isFirst = false),
    Item('f', isSelected = false, isFirst = false),
    Item('j', isSelected = false, isFirst = false),
    Item('k', isSelected = false, isFirst = false),
    Item('l', isSelected = false, isFirst = false)
)

