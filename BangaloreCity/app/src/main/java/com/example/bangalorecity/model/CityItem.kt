package com.example.bangalorecity.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CityItem(
    @StringRes val titleResourceId: Int,
    @StringRes val subtitleResourceId: Int,
    val latitude : Double,
    val longitude : Double,
    @StringRes val openCloseTime: Int,
    @StringRes val famousFor: Int,
    @DrawableRes val imageResourceId: Int,
    @DrawableRes val cityImageBanner: Int,
    @StringRes val cityDetails: Int,
    val category: Category
)

enum class Category{
    Coffee_Shops, Restaurants, Kid_Friendly, Parks, Shopping_Centers
}