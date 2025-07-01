package com.example.example8_lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.example8_lemonade.ui.theme.Example8_lemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Example8_lemonadeTheme {
                Lemonade(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize()
                )
            }
        }
    }
}


@Composable
fun Lemonade(modifier: Modifier) {

    var page by remember { mutableStateOf(1) }
    var count by remember { mutableStateOf(0) }
    var data: Pair<Int, Int> = when (page) {
        1 -> Pair<Int, Int>(R.drawable.lemon_tree, R.string.lemon_tree_des)
        2 -> Pair<Int, Int>(R.drawable.lemon_squeeze, R.string.lemon_des)
        3 -> Pair<Int, Int>(R.drawable.lemon_drink, R.string.glass_of_lemonade_des)
        else -> Pair<Int, Int>(R.drawable.lemon_restart, R.string.empty_glass_des)
    }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(data.first),
            contentDescription = stringResource(R.string.lemon_tree),
            modifier = Modifier.clickable {
                if (page < 4 && page != 2) {
                    page++
                } else if (page == 2) {
                    if (count == 0) {
                        count = (2..4).random()
                    } else if (count == 1) {
                        page++
                    } else {
                        count--
                    }
                } else {
                    page = 1
                    count = 0
                }
            }
        )
        Spacer(Modifier.height(16.dp))

        Text(text = stringResource(data.second))
    }
}


@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    Lemonade(
        Modifier
            .fillMaxSize()
            .wrapContentSize()
    )
}