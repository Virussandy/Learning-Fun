package com.example.example6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.example6.ui.theme.Example6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Example6Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BusinessCard(
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BusinessCard(modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.android_logo)
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxSize()
        ) {
            Image(
                painter = image,
                contentDescription = null,
                Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .background(
                        Color(0xFF0C2B15)
                    )
            )
            Spacer(Modifier.height(8.dp))
            Text(text = "Jennifer Deo", fontSize = 48.sp)
            Spacer(Modifier.height(8.dp))
            Text(text = "Android Developer Extraordinaire", color = Color(0xFF3ddc84))
        }

        Box(modifier.fillMaxSize().padding(0.dp,48.dp),
            contentAlignment = Alignment.BottomCenter,) {
            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                IconText(Icons.Filled.Call, "+11 (123) 444 555 666")
                Spacer(Modifier.height(4.dp))
                IconText(Icons.Filled.Share, "@AndroidDev")
                Spacer(Modifier.height(4.dp))
                IconText(Icons.Filled.Email, "jen.doe@android.com")
            }
        }
    }
}


@Composable
fun IconText(icon: ImageVector, text: String, modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {

        Icon(icon, contentDescription = null, tint = Color(0xFF3ddc84))
        Spacer(Modifier.width(8.dp))
        Text(text = text, color = Color(0xFF0C2B15))
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    Example6Theme {
        BusinessCard(modifier = Modifier)
    }
}