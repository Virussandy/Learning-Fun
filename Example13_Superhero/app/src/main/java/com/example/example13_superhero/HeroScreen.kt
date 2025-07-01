package com.example.example13_superhero

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.example13_superhero.data.HeroSource
import com.example.example13_superhero.data.heroes

class HeroScreen {

    @Composable
    fun SuperHeroItem(hero: HeroSource, modifier: Modifier = Modifier) {
        Card(
            elevation = CardDefaults.cardElevation(2.dp),
            modifier = modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium))
            ) {
                Column(
                    modifier = modifier
                        .weight(.8f)
                        .padding(end = dimensionResource(R.dimen.padding_medium))
                ) {
                    Text(
                        text = stringResource(hero.nameRes),
                        style = MaterialTheme.typography.displaySmall
                    )
                    Text(
                        text = stringResource(hero.descriptionRes),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Box(
                    modifier = modifier
                        .weight(.2f)
                        .width(72.dp)
                        .height(72.dp)
                        .clip(shape = MaterialTheme.shapes.small)
                ) {
                    Image(
                        painter = painterResource(hero.imageRes),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun SuperHeroPreview() {
        SuperHeroItem(heroes[0])
    }
}