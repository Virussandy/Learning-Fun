package com.example.example9_art_space

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.example9_art_space.ui.theme.Example9_Art_SpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Example9_Art_SpaceTheme {
                Surface() {
                    ArtSpace(modifier = Modifier)
                }
            }
        }
    }
}

@Composable
fun ArtSpace(modifier: Modifier = Modifier) {

    var currentItem by remember { mutableStateOf(1) }
    var imageId = R.drawable.art1
    var title = "Chestnut-shouldered Grass-Parakeet (Euphema pulchella)"
    var artistName = "Elizabeth Gould"
    var year = 2024


    when(currentItem){
        1 -> {
            imageId = R.drawable.art1
            title = "Chestnut-shouldered Grass-Parakeet (Euphema pulchella)"
            artistName = "Elizabeth Gould "
            year = 2024
        }
        2 -> {
            imageId = R.drawable.art2
            title = "Red bird of paradise vector animal art print"
            artistName = "Gould and William Matthew Hart"
            year = 2023
        }

        3 -> {
            imageId = R.drawable.art3
            title = "Mano Tree vintage illustration vector"
            artistName = "original artwork "
            year = 2022
        }

        4 -> {
            imageId = R.drawable.art4
            title = "Cover of A history of the earth and animated nature"
            artistName = "Oliver Goldsmith"
            year = 1820
        }

        5 -> {
            imageId = R.drawable.art5
            title = "Vintage parrot sticker, bird illustration vector, remixed from the artworks by"
            artistName = "George Edwards"
            year = 2024
        }

    }
    Column(
        modifier = modifier
            .fillMaxSize().verticalScroll(rememberScrollState())
            .padding(top = 16.dp, start = 16.dp, bottom = 24.dp, end = 16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageCompose(imageId = imageId, modifier = modifier)

        Spacer(modifier = Modifier.height(52.dp))

        Column(modifier = modifier
            .fillMaxWidth()
            .background(Color(226, 227, 253))
            .padding(16.dp)) {
            TextCompose(text =title, modifier = modifier,fontSize = 22.sp, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Thin)
            Row {
                TextCompose(text = artistName, fontSize = 16.sp,style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                Spacer(Modifier.width(2.dp))
                TextCompose(text = "($year) ", fontSize = 16.sp,style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Thin)
            }
        }

        Spacer(modifier = modifier.height(16.dp))

        Row (modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
            ButtonComposable("Previous",{if(currentItem in 2..5)currentItem--})
            ButtonComposable("Next",{if(currentItem in 1..4) currentItem++})
        }
    }
}

@Composable
fun ImageCompose(imageId:Int,modifier: Modifier = Modifier) {
    Card(modifier = modifier.wrapContentSize(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Image(
            painter = painterResource(imageId),
            contentDescription = null,
            modifier = modifier.padding(32.dp)
        )
    }

}

@Composable
fun TextCompose(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit,
    style: TextStyle,
    fontWeight: FontWeight,) {
    Text(text = text,
        fontSize = fontSize,
        modifier = modifier,
        style = style,
        fontWeight = fontWeight)
}

@Composable
fun ButtonComposable(buttonName:String,onClick:()->Unit,modifier: Modifier = Modifier) {
    Button(onClick = onClick) {
        Text(text = buttonName)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    Example9_Art_SpaceTheme {
        ArtSpace(modifier = Modifier)
    }
}