package com.example.example13_superhero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.example13_superhero.data.heroes
import com.example.example13_superhero.ui.theme.Example13_SuperheroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Example13_SuperheroTheme {
                SuperHeroApp()
            }
        }
    }
}

@Composable
fun SuperHeroApp(modifier: Modifier = Modifier) {
    Scaffold(topBar = { TopAppBar(modifier) }, modifier = modifier.fillMaxSize()) { innerPadding ->
        SuperHeroList(
            modifier.padding(
                innerPadding
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(title = { Text(text = stringResource(R.string.app_name), style = MaterialTheme.typography.displayLarge) })
}

@Composable
fun SuperHeroList(modifier: Modifier = Modifier) {

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.padding_small)
        )
    ) {
        items(heroes) { hero ->
            HeroScreen().SuperHeroItem(hero)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Example13_SuperheroTheme {
    }
}