package com.mollosradix.deals.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.ImageLoader
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mollosradix.deals.R
import com.mollosradix.deals.network.ProxyImageLoader
import com.mollosradix.deals.viewmodel.DealsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DealsScreen(
    navController: NavController,
    viewModel: DealsViewModel = viewModel(),
) {
    val deals by viewModel.deals.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val hasMoreItems by viewModel.hasMoreItems.collectAsState()
    val context = LocalContext.current
    val imageLoader = remember {
        ImageLoader.Builder(context).components {
            add(
                OkHttpNetworkFetcherFactory(
                    callFactory = ProxyImageLoader.getUnsafeOkHttpClient()
                )
            )
        }.build()
    }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text("OffnBuy", textAlign = TextAlign.Center, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold) }, actions = {
            IconButton(onClick = { navController.navigate("search") }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        }, windowInsets = WindowInsets(
            top = dimensionResource(id = R.dimen.no_space),
            bottom = dimensionResource(id = R.dimen.no_space)
        ),
            expandedHeight = 50.dp
        )
    }, floatingActionButton = {
        FloatingActionButton(
            modifier = Modifier.padding(
                bottom = dimensionResource(R.dimen.smallMedium),
                end = dimensionResource(R.dimen.smallMedium)
            ), onClick = { viewModel.reload() }) {
            Icon(Icons.Default.Refresh, contentDescription = "refresh")
        }
    }) { paddingValues ->

        if (isLoading && deals.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    top = paddingValues.calculateTopPadding(),
                    bottom = dimensionResource(R.dimen.no_space),
                    start = dimensionResource(R.dimen.smallMedium),
                    end = dimensionResource(R.dimen.smallMedium)
                ),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(
                    items = deals,
                    key = { "${it.deal_id}_${it.posted_on}" }
                ) { deal ->
                    DealCard(
                        title = deal.title ?: "",
                        imageUrl = deal.image ?: "",
                        price = deal.price ?: "",
                        originalPrice = deal.originalPrice ?: "",
                        discount = deal.discount ?: "",
                        store = deal.store ?: "",
                        timeAgo = viewModel.getTimeAgo(deal.posted_on) ?: "",
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, deal.url.toUri())
                            context.startActivity(intent)
                        },
                        url = deal.url ?: "",
                        context = context,
                        imageLoader = imageLoader
                    )
                }

                if (isLoading) {
                    item(span = { GridItemSpan(2) }) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                if (hasMoreItems && !isLoading) {
                    item(span = { GridItemSpan(2) }) {
                        LaunchedEffect(Unit) {
                            viewModel.loadMoreDeals()
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DealCard(
    title: String,
    imageUrl: String,
    price: String,
    originalPrice: String,
    discount: String,
    store: String,
    timeAgo: String,
    onClick: () -> Unit,
    url: String,
    context: Context,
    imageLoader: ImageLoader
) {
    Surface(
        modifier = Modifier.fillMaxWidth(), border = BorderStroke(
            color = MaterialTheme.colorScheme.secondary, width = 0.5.dp
        ), shape = MaterialTheme.shapes.medium
    ) {
        Box(contentAlignment = Alignment.TopEnd) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                // Product Image with error handling
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        GlideImage(
                            model = imageUrl,
                            contentDescription = title,
                            contentScale = ContentScale.FillWidth
                        )
                    }
                Spacer(modifier = Modifier.height(8.dp))
                // Title
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium,
                    maxLines = 2,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(4.dp))
                // Price Row
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (price != "") {
                        Text(
                            text = "₹$price",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    if (originalPrice != "") {
                        Text(
                            text = "₹$originalPrice",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color.Gray,
                                textDecoration = androidx.compose.ui.text.style.TextDecoration.LineThrough
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(2.dp))
                // Time and Store Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = timeAgo,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray
                    )
                    Spacer(Modifier.width(dimensionResource(R.dimen.smallMedium)))
                    Text(
                        text = store,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.End
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                // Shop Now Button
                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    onClick = onClick,
                    shape = MaterialTheme.shapes.extraLarge
                ) {
                    Text("Shop Now", fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.small)))

                Text(
                    text = "Prices and stock may vary.",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimensionResource(R.dimen.small))
                )
            }
            // Top Row: Discount badge and Share icon

            // Discount Badge (Outlined)
            Row(modifier = Modifier.padding(dimensionResource(R.dimen.smallMedium))) {
                if (discount.isNotEmpty()) {
                    Surface(
                        shape = MaterialTheme.shapes.small,
                        border = BorderStroke(
                            width = 0.5.dp, color = MaterialTheme.colorScheme.secondary
                        ),
                    ) {
                        Text(
                            text = discount,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 6.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Surface(
                    shape = MaterialTheme.shapes.small, border = BorderStroke(
                        width = 0.5.dp, color = MaterialTheme.colorScheme.secondary
                    ), shadowElevation = 0.dp
                ) {
                    // Share Icon
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .height(26.dp)
                            .width(26.dp)
                            .padding(dimensionResource(R.dimen.smallMedium))
                            .align(Alignment.CenterVertically)
                            .clickable {
                                val sendIntent: Intent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, "$url \n\n $price")
                                    type = "text/plain"
                                }
                                val shareIntent = Intent.createChooser(sendIntent, null)
                                context.startActivity(shareIntent)
                            })
                }
            }
        }
    }
}

