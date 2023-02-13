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
    var hasWinner by remember { mutableStateOf(false) }
    var gameTurn by remember { mutableStateOf("X")}
    var gameState = remember {
        mutableStateListOf<String>(
            "", "", "",
            "", "", "",
            "", "", "",
        )
    }
    // TODO: Use this later on in order to highlight the winning row in red.
    var winPosition:  List<Boolean>? = remember { mutableStateListOf<Boolean>() }

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
                            if (!hasWinner && gameState[index] == "") {
                                gameState[index] = gameTurn

                                // Must check winner before changing the turn
                                hasWinner = hasWinner(gameState, gameTurn)

                                // TEST
                                if (hasWinner) {
                                    println(getWinPosition(gameState, gameTurn))
                                }

                                if (!hasWinner) {
                                    // No longer need to change turns if we already have a winner :D
                                    gameTurn = if (gameTurn == "X") "O" else "X"
                                }
                            }
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
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = getInfoText(gameTurn, hasWinner),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(64.dp))
        Button(onClick = {
            for (i in gameState.indices) {
                gameState[i] = ""
            }
            gameTurn = "X"
            hasWinner = false
        }) {
            Text(text = stringResource(R.string.reset_button))
        }
    }
}

fun getInfoText(gameTurn: String, hasWinner: Boolean): String {
    return if (hasWinner) {
        "Game over! $gameTurn wins!"
    } else {
        "Turn: $gameTurn"
    }
}

val horizontalWin_1 = listOf<Boolean>(
    true, true, true,
    false, false, false,
    false, false, false
)
val horizontalWin_2 = listOf<Boolean>(
    false, false, false,
    true, true, true,
    false, false, false
)
val horizontalWin_3 = listOf<Boolean>(
    false, false, false,
    false, false, false,
    true, true, true
)
val verticalWin_1 = listOf<Boolean>(
    true, false, false,
    true, false, false,
    true, false, false
)
val verticalWin_2 = listOf<Boolean>(
    false, true, false,
    false, true, false,
    false, true, false
)
val verticalWin_3 = listOf<Boolean>(
    false, false, true,
    false, false, true,
    false, false, true
)
val transversalWin_1 = listOf<Boolean>(
    true, false, false,
    false, true, false,
    false, false, true
)
val transversalWin_2 = listOf<Boolean>(
    false, false, true,
    false, true, false,
    true, false, false
)

val winPositions = listOf<List<Boolean>>(
    horizontalWin_1,
    horizontalWin_2,
    horizontalWin_3,
    verticalWin_1,
    verticalWin_2,
    verticalWin_3,
    transversalWin_1,
    transversalWin_2
)

fun isWinState(gameState: List<String>, gameTurn: String, winState: List<Boolean>): Boolean {
    val booleanState = gameState.map { it == gameTurn }
    // TODO: Fix this. We cannot compare the whole list. We need to compare the True indices of the winState with the
    // game state, otherwise you can only compute a win if the winner has only played three times.
    return booleanState == winState
}

fun hasWinner(gameState: List<String>, gameTurn: String): Boolean {
    return winPositions.any {
        isWinState(gameState, gameTurn, it)
    }
}

fun getWinPosition(gameState: List<String>, gameTurn: String): List<Boolean>? {
    val booleanState = gameState.map { it == gameTurn }
    return winPositions.find {
        it == booleanState
    }
}
