package com.example.bangalorecity.ui

import com.example.bangalorecity.data.LocalCityDataProvider
import com.example.bangalorecity.model.Category
import com.example.bangalorecity.model.CityItem

data class CityUiState(
    val categories : Map<Category, List<CityItem>> = emptyMap(),
    val currentCategory: Category =  Category.Coffee_Shops,
    val currentSelectedCityItem : CityItem = LocalCityDataProvider.defaultData,
    val isShowingHomepage: Boolean = true
){
    val currentCategoryCityItem:List<CityItem> by lazy { categories[currentCategory]!! }
}