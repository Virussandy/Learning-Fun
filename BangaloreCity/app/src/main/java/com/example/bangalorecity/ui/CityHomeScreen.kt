package com.example.bangalorecity.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.bangalorecity.R
import com.example.bangalorecity.model.Category
import com.example.bangalorecity.model.CityItem
import com.example.bangalorecity.ui.utils.CityContentType
import com.example.bangalorecity.ui.utils.CityNavigationType

@Composable
fun CityHomeScreen(
    navigationType: CityNavigationType,
    contentType: CityContentType,
    cityUiState: CityUiState,
    onTabPressed: (Category) -> Unit,
    onCityCardPressed: (CityItem) -> Unit,
    onDetailScreenBackedPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navigationItemContentList = listOf(
        NavigationItemContent(
            categories = Category.Coffee_Shops,
            icon = R.drawable.ic_coffee,
            text = stringResource(R.string.coffee_shop)
        ),
        NavigationItemContent(
            categories = Category.Restaurants,
            icon = R.drawable.ic_restaurants,
            text = stringResource(R.string.restaurants)
        ),
        NavigationItemContent(
            categories = Category.Kid_Friendly,
            icon = R.drawable.ic_kids,
            text = stringResource(R.string.kid_friendly_places)
        ),
        NavigationItemContent(
            categories = Category.Parks,
            icon = R.drawable.ic_parks,
            text = stringResource(R.string.parks)
        ),
        NavigationItemContent(
            categories = Category.Shopping_Centers,
            icon = R.drawable.ic_shoppings,
            text = stringResource(R.string.shopping_centers)
        )
    )

    if (navigationType == CityNavigationType.PERMANENT_NAVIGATION_DRAWER) {
        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet {
                    NavigationDrawerContent(
                        selectedDestination = cityUiState.currentCategory,
                        onTabPressed = onTabPressed,
                        navigationItemContentList = navigationItemContentList,
                        modifier = Modifier
                            .wrapContentWidth()
                            .fillMaxHeight()
//                            .background(MaterialTheme.colorScheme.inverseOnSurface)
                            .padding(
                                dimensionResource(R.dimen.medium_large_padding)
                            )
                    )
                }
            }
        ) {
            CityAppContent(
                navigationType = navigationType,
                contentType = contentType,
                cityUiState = cityUiState,
                onTabPressed = onTabPressed,
                onCityCardPressed = onCityCardPressed,
                navigationItemContentList = navigationItemContentList,
                modifier = modifier
            )
        }
    } else {
        if (cityUiState.isShowingHomepage) {
            CityAppContent(
                navigationType = navigationType,
                contentType = contentType,
                cityUiState = cityUiState,
                onTabPressed = onTabPressed,
                onCityCardPressed = onCityCardPressed,
                navigationItemContentList = navigationItemContentList,
                modifier = modifier
            )
        } else {
            CityDetailScreen(
                modifier = Modifier,
                cityUiState = cityUiState,
                isFullScreen = true,
                onBackedPressed = onDetailScreenBackedPressed,
            )
        }
    }
}

@Composable
private fun CityAppContent(
    navigationType: CityNavigationType,
    contentType: CityContentType,
    cityUiState: CityUiState,
    onTabPressed: (Category) -> Unit,
    onCityCardPressed: (CityItem) -> Unit,
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier) {
        Row {
            AnimatedVisibility(
                visible = navigationType == CityNavigationType.NAVIGATION_RAIL
            ) {
                CityNavigationRail(
                    modifier = Modifier,
                    currentTab = cityUiState.currentCategory,
                    onTabPressed = onTabPressed,
                    navigationItemContentList = navigationItemContentList
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
//                    .background(MaterialTheme.colorScheme.inverseOnSurface)
            ) {
                if (contentType == CityContentType.LIST_AND_DETAILS) {
                    CityListAndDetailedContent(
                        cityUiState = cityUiState,
                        onCategoryCardPressed = onCityCardPressed,
                        modifier = modifier.weight(1f)
                    )
                } else {
                    CityListOnlyContent(
                        cityUiState = cityUiState,
                        onCategoryCardPressed = onCityCardPressed,
                        modifier = modifier.weight(1f)
                    )
                }
                AnimatedVisibility(
                    visible = navigationType == CityNavigationType.BOTTOM_NAVIGATION
                ) {
                    CityBottomNavigation(
                        modifier = Modifier,
                        navigationItemContentList = navigationItemContentList,
                        onTabPressed = onTabPressed,
                        currentTab = cityUiState.currentCategory
                    )
                }
            }
        }
    }

}

@Composable
private fun CityBottomNavigation(
    modifier: Modifier = Modifier,
    navigationItemContentList: List<NavigationItemContent>,
    currentTab: Category,
    onTabPressed: (Category) -> Unit
) {
    NavigationBar {
        for (item in navigationItemContentList) {
            NavigationBarItem(
                selected = currentTab == item.categories,
                onClick = { onTabPressed(item.categories) },
                label = { Text(text = item.text) },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = null,
                        modifier = modifier.height(height = dimensionResource(R.dimen.icon_size))
                    )
                }
            )
        }
    }
}

@Composable
private fun CityNavigationRail(
    modifier: Modifier = Modifier,
    currentTab: Category,
    onTabPressed: (Category) -> Unit,
    navigationItemContentList: List<NavigationItemContent>
) {

    NavigationRail(modifier = modifier) {
        for (item in navigationItemContentList) {
            NavigationRailItem(
                selected = currentTab == item.categories,
                onClick = { onTabPressed(item.categories) },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = null,
                        modifier = modifier.height(height = dimensionResource(R.dimen.icon_size))
                    )
                },
            )
        }
    }

}

@Composable
private fun NavigationDrawerContent(
    selectedDestination: Category,
    onTabPressed: (Category) -> Unit,
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {

    for (navItem in navigationItemContentList) {
        NavigationDrawerItem(
            selected = selectedDestination == navItem.categories,
            label = {
                Text(
                    text = navItem.text,
                    //modifier = modifier.padding(horizontal = dimensionResource(R.dimen.medium_padding))
                )
            },
            icon = {
                Icon(
                    painter = painterResource(navItem.icon),
                    contentDescription = null,
                    modifier = modifier.height(height = dimensionResource(R.dimen.icon_size))
                )
            },
            colors = NavigationDrawerItemDefaults.colors(unselectedBadgeColor = Color.Transparent),
            onClick = { onTabPressed(navItem.categories) }
        )
    }
}

private data class NavigationItemContent(
    val categories: Category,
    val icon: Int,
    val text: String
)