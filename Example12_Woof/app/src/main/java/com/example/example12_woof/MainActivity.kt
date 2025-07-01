package com.example.example12_woof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.example12_woof.data.Dog
import com.example.example12_woof.data.dogs
import com.example.example12_woof.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                WoofApp(
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun WoofApp(modifier: Modifier = Modifier) {
    val layoutDirection = LocalLayoutDirection.current
    Scaffold(topBar = {
        WoofTopAppBar()
    }) { innerPadding ->
        WoofList(dogs = dogs, modifier = modifier.padding(innerPadding))
    }
}

@Composable
fun WoofList(dogs: List<Dog>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_small))
    ) {
        items(dogs) { Items(it) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WoofTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(title = {
        Row(horizontalArrangement = Arrangement.Center) {
            Image(
                painter = painterResource(R.drawable.ic_woof_logo), contentDescription = null,
                Modifier
                    .size(
                        dimensionResource(R.dimen.image_size)
                    )
                    .padding(dimensionResource(R.dimen.padding_small))
            )
            Text(text = "Woof", style = MaterialTheme.typography.displayLarge)
        }
    }, modifier = modifier)

}

@Composable
fun DogItemButton(expended: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expended) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun Items(item: Dog, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(if (expanded) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.primaryContainer)
    Card(
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 25.dp,
            bottomStart = 25.dp,
            bottomEnd = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessVeryLow
                    )
                )
                .background(color = color)

        ) {
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium))
            ) {
                Image(
                    painter = painterResource(item.photo),
                    contentDescription = null,
                    modifier
                        .width(50.dp)
                        .height(50.dp)
                        .clip(MaterialTheme.shapes.small),

                    contentScale = ContentScale.Crop,
                )

                Spacer(Modifier.width(8.dp))
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(item.name),
                        style = MaterialTheme.typography.displayMedium
                    )
                    Text(
                        text = stringResource(R.string.years_old, item.age),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Spacer(Modifier.weight(1f))

                DogItemButton(expended = expanded, onClick = {
                    expanded = !expanded
                })
            }

            if (expanded) {
                DogHobby(
                    dogHobby = item.hobbies,
                    modifier = modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_medium)
                    )
                )
            }
        }
    }
}

@Composable
fun DogHobby(@StringRes dogHobby: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.about),
            style = MaterialTheme.typography.labelSmall
        )

        Text(
            text = stringResource(dogHobby),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WoofLightPreview() {
    AppTheme(darkTheme = false) {
        WoofApp()
    }
}

@Preview(showBackground = true)
@Composable
fun WoofDarkPreview() {
    AppTheme(darkTheme = true) {
        WoofApp()
    }
}