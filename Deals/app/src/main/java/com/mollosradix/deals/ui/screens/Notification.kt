package com.mollosradix.deals.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mollosradix.deals.model.DealItem
import com.mollosradix.deals.viewmodel.NotificationViewModel

@Composable
fun NotificationScreen( viewModel: NotificationViewModel = viewModel()) {

    val notifications by viewModel.deals.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Notifications",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            ),
            modifier = Modifier.padding(vertical = 16.dp)
        )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(notifications) { index ->
                    NotificationItems(index = index, viewModel = viewModel)
                }
            }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun NotificationItems(index: DealItem, viewModel: NotificationViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .height(60.dp)
                    .height(60.dp),
                contentAlignment = Alignment.Center
            ) {
                GlideImage(
                    model = index.image,
                    contentDescription = index.title,
                    contentScale = ContentScale.Fit
                )
            }
            Column(modifier = Modifier.weight(1f).padding(start = 8.dp)) {
                Text(text = index.title, style = MaterialTheme.typography.titleSmall)
                Text(text = index.price, style = MaterialTheme.typography.bodyMedium)
                Text(
                    //                text = SimpleDateFormat("hh:mm a, dd MMM yyyy").format(Date(index.posted_on)),
                    text = viewModel.getTimeAgo(index.posted_on),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}