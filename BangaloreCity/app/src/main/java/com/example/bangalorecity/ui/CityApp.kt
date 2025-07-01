package com.example.bangalorecity.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bangalorecity.model.Category
import com.example.bangalorecity.model.CityItem
import com.example.bangalorecity.ui.utils.CityContentType
import com.example.bangalorecity.ui.utils.CityNavigationType


@Composable
fun CityApp(
    modifier: Modifier = Modifier,
    windowSize: WindowWidthSizeClass
) {
    val viewModel: CityViewModel = viewModel()
    val cityUiState = viewModel.uiState.collectAsState().value
    val navigationType: CityNavigationType
    val contentType: CityContentType

    when(windowSize){
        WindowWidthSizeClass.Compact -> {
            navigationType = CityNavigationType.BOTTOM_NAVIGATION
            contentType = CityContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Medium -> {
            navigationType = CityNavigationType.NAVIGATION_RAIL
            contentType = CityContentType.LIST_AND_DETAILS
        }
        WindowWidthSizeClass.Expanded ->{
            navigationType = CityNavigationType.PERMANENT_NAVIGATION_DRAWER
            contentType = CityContentType.LIST_AND_DETAILS
        }
        else -> {
            navigationType = CityNavigationType.BOTTOM_NAVIGATION
            contentType = CityContentType.LIST_ONLY
        }
    }

    CityHomeScreen(
        navigationType = navigationType,
        contentType = contentType,
        cityUiState = cityUiState,
        onTabPressed = { category: Category ->
            viewModel.updateCurrentCityCategory(category)
            viewModel.resetHomeScreenStatus()
        },
        onCityCardPressed = { cityItem: CityItem ->
            viewModel.updateDetailScreen(cityItem)
        },
        onDetailScreenBackedPressed = {
            viewModel.resetHomeScreenStatus()
        },
        modifier = modifier
    )
}