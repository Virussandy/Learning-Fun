package com.example.example5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.example5.ui.theme.Example5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Example5Theme {
                Surface() {
                    ComposeQuadrant(modifier = Modifier)
                }
            }
        }
    }
}

@Composable
fun ComposeQuadrant(modifier: Modifier) {
    Column(modifier = modifier) {
        Row (modifier=modifier.weight(.5f)){
            Box(
                modifier = modifier
                    .weight(0.5F)
                    .background(Color(0xFFEADDFF))
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.fillMaxSize()
                        .padding(16.dp)) {
                    Text(text = "Text composable",  modifier.padding(0.dp,0.dp,0.dp,16.dp),fontWeight = FontWeight.Bold)
                    Text(
                        text = "Displays text and follows the recommended Material Design guidelines.",
                        modifier,
                        textAlign = TextAlign.Justify
                    )
                }
            }
            Box(
                modifier = modifier
                    .weight(0.5F)
                    .background(Color(0xFFD0BCFF))
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.fillMaxSize()
                        .padding(16.dp)) {
                    Text(text = "Image composable",  modifier.padding(0.dp,0.dp,0.dp,16.dp),fontWeight = FontWeight.Bold)
                    Text(
                        text = "Creates a composable that lays out and draws a given Painter class object.",
                        modifier,
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
        Row (modifier=modifier.weight(.5f)){
            Box(
                modifier = modifier
                    .weight(0.5F)
                    .background(Color(0xFFB69DF8))
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.fillMaxSize()
                        .padding(16.dp)) {
                    Text(text = "Row composable",  modifier.padding(0.dp,0.dp,0.dp,16.dp),fontWeight = FontWeight.Bold)
                    Text(
                        text = "A layout composable that places its children in a horizontal sequence.",
                        modifier,
                        textAlign = TextAlign.Justify
                    )
                }
            }
            Box(
                modifier = modifier
                    .weight(0.5F)
                    .background(Color(0xFFF6EDFF))
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.fillMaxSize()
                        .padding(16.dp)) {
                    Text(text = "Column composable",  modifier.padding(0.dp,0.dp,0.dp,16.dp),fontWeight = FontWeight.Bold)
                    Text(
                        text = "A layout composable that places its children in a vertical sequence.",
                        modifier,
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Example5Theme {
        ComposeQuadrant(modifier = Modifier)
    }
}