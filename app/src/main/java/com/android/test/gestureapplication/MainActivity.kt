package com.android.test.gestureapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

val letters = listOf(
    "A", "B", "C", "D", "E", "E",
    "F", "G", "H", "I", "J", "J",
    "K", "L", "M", "N", "O", "O",
    "P", "Q", "R", "S", "T", "T"
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameLettersGrid()
        }
    }

    @Composable
    fun GameLettersGrid() {
        GestureBox()
    }

//            ConstraintLayout {
//                val (image, grid) = createRefs()
//                Image(
//                    painter = painterResource(R.drawable.field_game_1),
//                    contentDescription = null,
//                    contentScale = ContentScale.Fit,
//                    modifier = Modifier.constrainAs(image) {
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                        bottom.linkTo(parent.bottom)
//                    }
//                )
//
//                LettersGrid(
//                    modifier = Modifier
//                        .constrainAs(grid) {
//                            start.linkTo(image.start)
//                            bottom.linkTo(image.bottom)
//                            top.linkTo(image.top)
//                        }
//                        .padding(horizontal = 20.dp)
//                )
//            }
//        }
}

//    @Composable
//    fun LettersGrid(
//        modifier: Modifier
//    ) {
//
//        var selectedWord by remember {
//            mutableStateOf("")
//        }
//        Box(
//            modifier = Modifier.size(50.dp)
//        ) {
//
//            LazyVerticalGrid(
//                columns = GridCells.Fixed(6),
//                modifier = modifier,
//                verticalArrangement = Arrangement.spacedBy(1.dp)
//            ) {
//                items(letters) { item ->
//                    LetterItem(
//                        letter = item,
//                        modifier = modifier,
//                        selectedWord = selectedWord,
//                        onWordSelected = { selectedWord = it }
//                    )
//                }
//            }
//        }
//    }


//        Image(
//            modifier = Modifier
//                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
//                .pointerInput(Unit) {
//                    detectDragGestures(
//                        onDragStart = {
//                            visible = true
//                            isSelected = true
//                            Log.e("DRAG STARTED", "START")
//                        },
//                        onDrag = { change, dragAmount ->
//                            change.consume()
//                            offsetY += dragAmount.y
//                            Log.e("DRAG VERTICALLY", "DRAGGGING")
//
//                            offsetX += dragAmount.x
//                            Log.e("DRAG HORIZONTALLY", "DRAGGGING")
//                        },
//                        onDragEnd = {
//                            isSelected = false
//                            Log.e("DRAG STARTED", "START")
//                        }
//                    )
//                }
//                .height(90.dp)
//                .alpha(50F)
//                .clip(RoundedCornerShape(7.dp)),
//            contentScale = ContentScale.FillBounds,
//            painter = painterResource(id = R.drawable.backlight_word),
//            contentDescription = null
//        )


//      Box(
//        modifier = modifier.size(50.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(
//            text = letter,
//            style = LocalTextStyle.current.merge(
//                TextStyle(
//                    color = Color.White,
//                    fontSize = 30.sp,
//                    fontFamily = FontFamily(Font(R.font.titan_one_regular))
//                )
//            )
//        )
//        Text(
//            text = letter,
//            style = LocalTextStyle.current.merge(
//                TextStyle(
//                    color = colorResource(id = R.color.border_letter_color),
//                    fontSize = 30.sp,
//                    drawStyle = Stroke(width = 3f, join = StrokeJoin.Round),
//                    fontFamily = FontFamily(Font(R.font.titan_one_regular))
//                )
//            )
//        )
//    }
//}
