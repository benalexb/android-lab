package com.example.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.ui.theme.TicTacToeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeTheme {
                TicTacToeGame()
            }
        }
    }
}

@Preview()
@Composable
fun TicTacToeGame() {
    TicTacToeTheme {
        TicTacToeLayout(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TicTacToeLayout(modifier: Modifier = Modifier){
    var gameState = remember {
        mutableStateListOf<String>(
            "X", "", "",
            "O", "", "",
            "", "", "X",
        )
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(128.dp),
            modifier = Modifier.background(Color.Black),
            // content padding
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            ),
            content = {
                items(gameState.size) { index ->
                    Card(
                        backgroundColor = Color.White,
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth(),
                        elevation = 8.dp,
                        onClick = {
                            println("TEST! Card Clicked!")
                        }
                    ) {
                        Text(
                            text = gameState[index],
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(64.dp))
        Button(onClick = {
            for (i in gameState.indices) {
                gameState[i] = ""
            }
        }) {
            Text(text = stringResource(R.string.reset_button))
        }
    }
}
