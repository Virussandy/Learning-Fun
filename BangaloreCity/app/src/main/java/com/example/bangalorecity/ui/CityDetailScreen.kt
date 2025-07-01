package com.example.bangalorecity.ui

import android.widget.ToggleButton
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.bangalorecity.R
import com.example.bangalorecity.model.CityItem

@Composable
fun CityDetailScreen(
    modifier: Modifier = Modifier,
    cityUiState: CityUiState,
    isFullScreen: Boolean = false,
    onBackedPressed: () -> Unit,
) {
    BackHandler {
        onBackedPressed()
    }
    var toggle by remember { mutableStateOf(false) }
    Column(modifier = modifier.padding(WindowInsets.safeDrawing.asPaddingValues())) {

        Image(
            painter = painterResource(cityUiState.currentSelectedCityItem.cityImageBanner),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(dimensionResource(R.dimen.medium_padding))
                .drawBehind {
                    drawRect(Color.Gray)
                }
            ,
            contentScale = ContentScale.Crop
        )
        Row (Modifier.fillMaxWidth()){
            Text(
                text = stringResource(cityUiState.currentSelectedCityItem.titleResourceId),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = dimensionResource(R.dimen.medium_padding))
            )
            Switch(
                checked = toggle,
                onCheckedChange = {toggle = !toggle}
            )
        }
        Text(text = stringResource(cityUiState.currentSelectedCityItem.openCloseTime))
        Text(
            text = stringResource(cityUiState.currentSelectedCityItem.famousFor),
            modifier = Modifier.padding(vertical = dimensionResource(R.dimen.medium_padding))
        )
        AnimatedVisibility(
            visible = toggle,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            Text(text = stringResource(cityUiState.currentSelectedCityItem.cityDetails))
        }
    }
}