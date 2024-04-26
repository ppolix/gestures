package com.android.test.gestureapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

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

        Box(modifier = Modifier.fillMaxSize()) {
            ConstraintLayout {
                val (image, grid) = createRefs()

                Image(
                    painter = painterResource(R.drawable.field_game_1),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.constrainAs(image) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                )

                LettersGrid(
                    modifier = Modifier
                        .constrainAs(grid) {
                            start.linkTo(image.start)
                            bottom.linkTo(image.bottom)
                            top.linkTo(image.top)
                        }
                        .padding(horizontal = 20.dp)
                )
            }
            AnimatedGesture()
        }
    }


    @Composable
    fun LettersGrid(
        modifier: Modifier
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(6),
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            items(letters.size) { index ->
                LetterItem(
                    text = letters[index],
                    modifier = modifier
                )
            }
        }
    }

    @Composable
    fun LetterItem(
        text: String,
        modifier: Modifier
    ) {
        Box(
            modifier = modifier.size(50.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = LocalTextStyle.current.merge(
                    TextStyle(
                        color = Color.White,
                        fontSize = 30.sp,
                        fontFamily = FontFamily(Font(R.font.titan_one_regular))
                    )
                )
            )
            Text(
                text = text,
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

    @Preview(showSystemUi = false, showBackground = true)
    @Composable
    fun LettersGridPreview() {

    }
}