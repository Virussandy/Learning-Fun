package com.example.bangalorecity.ui

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.bangalorecity.R
import com.example.bangalorecity.model.CityItem

@Composable
fun CityListOnlyContent(
    modifier: Modifier = Modifier,
    cityUiState: CityUiState,
    onCategoryCardPressed: (CityItem) -> Unit
) {

    val cities = cityUiState.currentCategoryCityItem

    LazyColumn(
        modifier = modifier,
        contentPadding = WindowInsets.safeDrawing.asPaddingValues(),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.medium_padding)
        ),
    ) {
        itemsIndexed(cities){
           index, city -> CityCategoryListItem(
                modifier = Modifier,
                cityItem = city,
                selected = false,
                onCardClicked = { onCategoryCardPressed(city) }
            )
//            if(index != cities.lastIndex){
//                HorizontalDivider()
//            }
        }
    }
}

@Composable
fun CityListAndDetailedContent(
    modifier: Modifier = Modifier,
    cityUiState: CityUiState,
    onCategoryCardPressed: (CityItem) -> Unit
) {
    val cities = cityUiState.currentCategoryCityItem
    Row(modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly){
        LazyColumn(
            contentPadding = WindowInsets.statusBars.asPaddingValues(),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = dimensionResource(R.dimen.medium_large_padding)),
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen.medium_padding)
            )
        ) {
            items(cities){
                item: CityItem ->  CityCategoryListItem(
                    cityItem = item,
                    selected = cityUiState.currentSelectedCityItem.titleResourceId == item.titleResourceId,
                    onCardClicked = {
                        onCategoryCardPressed(item)
                    }
                )
            }
        }
        val activity = LocalActivity.current
        CityDetailScreen(
            modifier = Modifier.weight(1f),
            cityUiState = cityUiState,
            isFullScreen = false,
            onBackedPressed = {activity?.finish()}

        )
    }

}



@Composable
fun CityCategoryListItem(
    modifier: Modifier = Modifier,
    cityItem: CityItem,
    selected: Boolean,
    onCardClicked: () -> Unit
) {
    Card (
        modifier = modifier.padding(horizontal =  dimensionResource(R.dimen.medium_padding)),
        colors = CardDefaults.cardColors(
            containerColor = if(selected){
                MaterialTheme.colorScheme.primaryContainer
            }else{
                MaterialTheme.colorScheme.secondaryContainer
            }
        ),
        onClick = onCardClicked
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.large_padding))
        ) {
            Text(
                text = stringResource(cityItem.titleResourceId),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.medium_large_padding),
                    end = dimensionResource(R.dimen.medium_padding)
                ),
            )
            Text(
                text = stringResource(cityItem.subtitleResourceId),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}