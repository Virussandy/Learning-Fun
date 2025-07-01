package com.example.example14_30days

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.example14_30days.data.DaysModel
import com.example.example14_30days.data.dayData
import com.example.example14_30days.ui.theme.Example14_30DaysTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Example14_30DaysTheme {
                Scaffold(
                    topBar = { TopAppBar(modifier = Modifier) },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    DayApp(
                        daysData = dayData,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    androidx.compose.material3.TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.top_bar),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    )
}

@Composable
fun DayApp(daysData: List<DaysModel>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_small))
    ) {
        items(daysData) { item ->
            DayItem(item)
        }
    }
}

@Composable
fun DayItem(dataItem: DaysModel, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(modifier = modifier.padding(dimensionResource(R.dimen.padding_medium))) {
            Text(
                text = stringResource(dataItem.dayNumber),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = stringResource(dataItem.dayHeading),
                style = MaterialTheme.typography.bodyLarge
            )
            Image(
                painter = painterResource(dataItem.dayImage),
                contentDescription = null,
                Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(dataItem.dayDescription),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Example14_30DaysTheme {
        DayItem(dataItem = dayData[0])
    }
}