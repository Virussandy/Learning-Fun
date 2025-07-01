package com.mollosradix.deals.ui.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil3.ImageLoader
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import com.mollosradix.deals.R
import com.mollosradix.deals.model.DealItem
import com.mollosradix.deals.network.ProxyImageLoader
import com.mollosradix.deals.viewmodel.SearchViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onBackOperation: () -> Unit
) {
    val deals by viewModel.deals.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val hasMoreItems by viewModel.hasMoreItems.collectAsState()
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val listState = rememberLazyGridState()
    val scope = rememberCoroutineScope()
    val imageLoader = remember{ ImageLoader.Builder(context).components{ add(OkHttpNetworkFetcherFactory(callFactory = ProxyImageLoader.getUnsafeOkHttpClient())) }.build()}

    LaunchedEffect(Unit) {
        if(deals.isNotEmpty()){
            listState.scrollToItem(0)
        }
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                title = { Text("Search")},
                windowInsets = WindowInsets(
                    top = dimensionResource(id = R.dimen.no_space),
                    bottom = dimensionResource(id = R.dimen.no_space)
                ),
                expandedHeight = 50.dp,
                navigationIcon = {
                    IconButton(onClick = { onBackOperation() }, modifier = Modifier.padding(
                        dimensionResource(R.dimen.smallMedium)
                    )) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }

            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(PaddingValues(top = paddingValues.calculateTopPadding()))
        ) {
            // Search Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchBar(
                    searchQuery = searchQuery,
                    onSearchQueryChange = {viewModel.onSearchQueryChange(it)},
                    onSearch = {viewModel.executeSearch()},
                    listState = listState,
                    focusRequester = focusRequester,
                    scope = scope,
                    isLoading = isLoading,
                    dealItems = deals
                )
            }

            // Results
            if (isLoading && deals.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyVerticalGrid(
                    state = listState,
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(
                        top = dimensionResource(R.dimen.no_space),
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
}

@Composable
fun SearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    listState: LazyGridState,
    focusRequester: FocusRequester,
    scope: CoroutineScope,
    isLoading : Boolean,
    dealItems : List<DealItem>
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 0.dp, bottom = 8.dp)
            .background(color = MaterialTheme.colorScheme.onPrimary,
                shape = RoundedCornerShape(25.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = searchQuery,
            onValueChange = { onSearchQueryChange(it) },
            placeholder = { Text("Search deals...") },
            singleLine = true,
            modifier = Modifier
                .weight(1f)
                .padding(bottom = dimensionResource(R.dimen.small))
                .focusRequester(focusRequester),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    scope.launch {
                        listState.scrollToItem(0)
                    }
                    keyboardController?.hide()
                    onSearch()
                }
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,  // <-- remove underline
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )

        Button(
            onClick = {
                scope.launch {
                    listState.scrollToItem(0)
                }
                keyboardController?.hide()
                onSearch()
            },
            shape = RoundedCornerShape(topEnd = 25.dp, bottomEnd = 25.dp),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 18.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.White
            )
        }

        if (isLoading && dealItems.isNotEmpty()) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp)
                    .size(24.dp),
                strokeWidth = 2.dp
            )
        }
    }
}

