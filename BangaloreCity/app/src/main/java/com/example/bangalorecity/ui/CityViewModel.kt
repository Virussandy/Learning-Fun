package com.example.bangalorecity.ui

import androidx.lifecycle.ViewModel
import com.example.bangalorecity.data.LocalCityDataProvider
import com.example.bangalorecity.model.Category
import com.example.bangalorecity.model.CityItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CityViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(CityUiState())
    val uiState:StateFlow<CityUiState> = _uiState

    init {
        initializeUiState()
    }

    private fun initializeUiState(){
        val categories: Map<Category, List<CityItem>> = LocalCityDataProvider.getCityData().groupBy { it.category }
        _uiState.value = CityUiState(
            categories = categories,
            currentSelectedCityItem = categories[Category.Coffee_Shops]?.get(0) ?:LocalCityDataProvider.defaultData
        )
    }

    fun updateDetailScreen(cityItem: CityItem){
        _uiState.update {
            it.copy(
                currentSelectedCityItem = cityItem,
                isShowingHomepage = false
            )
        }
    }

    fun resetHomeScreenStatus(){
        _uiState.update {
            it.copy(
                currentSelectedCityItem = it.categories[it.currentCategory]?.get(0) ?: LocalCityDataProvider.defaultData,
                isShowingHomepage = true
            )
        }
    }

    fun updateCurrentCityCategory(category: Category){
        _uiState.update {
            it.copy(
                currentCategory = category
            )
        }
    }
}