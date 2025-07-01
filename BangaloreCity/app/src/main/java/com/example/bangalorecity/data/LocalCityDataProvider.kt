package com.example.bangalorecity.data

import com.example.bangalorecity.R
import com.example.bangalorecity.model.Category
import com.example.bangalorecity.model.CityItem

object LocalCityDataProvider {

    val defaultData = getCityData()[0]

    fun getCityData() : List<CityItem>{
        return listOf(
            CityItem(
                titleResourceId = R.string.place_1_title,
                subtitleResourceId = R.string.place_1_subtitle,
                latitude = 12.957572,
                longitude = 77.625604,
                openCloseTime = R.string.place_1_time,
                famousFor = R.string.place_1_famous,
                imageResourceId = R.drawable.place_1_image,
                cityImageBanner = R.drawable.place_1_banner,
                cityDetails = R.string.place_1_details,
                category = Category.Coffee_Shops
            ),
            CityItem(
                titleResourceId = R.string.place_2_title,
                subtitleResourceId = R.string.place_2_subtitle,
                latitude = 12.976347,
                longitude = 77.603318,
                openCloseTime = R.string.place_2_time,
                famousFor = R.string.place_2_famous,
                imageResourceId = R.drawable.place_2_image,
                cityImageBanner = R.drawable.place_2_banner,
                cityDetails = R.string.place_2_details,
                category = Category.Coffee_Shops
            ),
            CityItem(
                titleResourceId = R.string.place_3_title,
                subtitleResourceId = R.string.place_3_subtitle,
                latitude = 12.934533,
                longitude = 77.614173,
                openCloseTime = R.string.place_3_time,
                famousFor = R.string.place_3_famous,
                imageResourceId = R.drawable.place_3_image,
                cityImageBanner = R.drawable.place_3_banner,
                cityDetails = R.string.place_3_details,
                category = Category.Coffee_Shops
            ),
            CityItem(
                titleResourceId = R.string.place_4_title,
                subtitleResourceId = R.string.place_4_subtitle,
                latitude = 12.937524,
                longitude = 77.610113,
                openCloseTime = R.string.place_4_time,
                famousFor = R.string.place_4_famous,
                imageResourceId = R.drawable.place_4_image,
                cityImageBanner = R.drawable.place_4_banner,
                cityDetails = R.string.place_4_details,
                category = Category.Coffee_Shops
            ),
            CityItem(
                titleResourceId = R.string.place_5_title,
                subtitleResourceId = R.string.place_5_subtitle,
                latitude = 12.935161,
                longitude = 77.614361,
                openCloseTime = R.string.place_5_time,
                famousFor = R.string.place_5_famous,
                imageResourceId = R.drawable.place_5_image,
                cityImageBanner = R.drawable.place_5_banner,
                cityDetails = R.string.place_5_details,
                category = Category.Coffee_Shops
            ),
            CityItem(
                titleResourceId = R.string.place_6_title,
                subtitleResourceId = R.string.place_6_subtitle,
                latitude = 12.971891,
                longitude = 77.641151,
                openCloseTime = R.string.place_6_time,
                famousFor = R.string.place_6_famous,
                imageResourceId = R.drawable.toit,
                cityImageBanner = R.drawable.toit_banner,
                cityDetails = R.string.place_6_details,
                category = Category.Restaurants
            ),
            CityItem(
                titleResourceId = R.string.place_7_title,
                subtitleResourceId = R.string.place_7_subtitle,
                latitude = 12.971956,
                longitude = 77.596321,
                openCloseTime = R.string.place_7_time,
                famousFor = R.string.place_7_famous,
                imageResourceId = R.drawable.airlines_hotel,
                cityImageBanner = R.drawable.airlines_hotel_banner,
                cityDetails = R.string.place_7_details,
                category = Category.Restaurants
            ),
            CityItem(
                titleResourceId = R.string.place_8_title,
                subtitleResourceId = R.string.place_8_subtitle,
                latitude = 12.971804,
                longitude = 77.603869,
                openCloseTime = R.string.place_8_time,
                famousFor = R.string.place_8_famous,
                imageResourceId = R.drawable.nagarjuna,
                cityImageBanner = R.drawable.nagarjuna_banner,
                cityDetails = R.string.place_8_details,
                category = Category.Restaurants
            ),
            CityItem(
                titleResourceId = R.string.place_9_title,
                subtitleResourceId = R.string.place_9_subtitle,
                latitude = 12.933828,
                longitude = 77.614480,
                openCloseTime = R.string.place_9_time,
                famousFor = R.string.place_9_famous,
                imageResourceId = R.drawable.truffles,
                cityImageBanner = R.drawable.truffles_banner,
                cityDetails = R.string.place_9_details,
                category = Category.Restaurants
            ),
            CityItem(
                titleResourceId = R.string.place_10_title,
                subtitleResourceId = R.string.place_10_subtitle,
                latitude = 12.971389,
                longitude = 77.617956,
                openCloseTime = R.string.place_10_time,
                famousFor = R.string.place_10_famous,
                imageResourceId = R.drawable.rim_naam,
                cityImageBanner = R.drawable.rim_naam_banner,
                cityDetails = R.string.place_10_details,
                category = Category.Restaurants
            ),
            CityItem(
                titleResourceId = R.string.place_11_title,
                subtitleResourceId = R.string.place_11_subtitle,
                latitude = 12.976347,
                longitude = 77.592928,
                openCloseTime = R.string.place_11_time,
                famousFor = R.string.place_11_famous,
                imageResourceId = R.drawable.jawahar_bal_bhavan,
                cityImageBanner = R.drawable.jawahar_bal_bhavan_banner,
                cityDetails = R.string.place_11_details,
                category = Category.Kid_Friendly
            ),
            CityItem(
                titleResourceId = R.string.place_12_title,
                subtitleResourceId = R.string.place_12_subtitle,
                latitude = 13.011097,
                longitude = 77.589550,
                openCloseTime = R.string.place_12_time,
                famousFor = R.string.place_12_famous,
                imageResourceId = R.drawable.snow_city,
                cityImageBanner = R.drawable.snow_city_banner,
                cityDetails = R.string.place_12_details,
                category = Category.Kid_Friendly
            ),
            CityItem(
                titleResourceId = R.string.place_13_title,
                subtitleResourceId = R.string.place_13_subtitle,
                latitude = 13.011540,
                longitude = 77.588890,
                openCloseTime = R.string.place_13_time,
                famousFor = R.string.place_13_famous,
                imageResourceId = R.drawable.fun_world,
                cityImageBanner = R.drawable.fun_world_banner,
                cityDetails = R.string.place_13_details,
                category = Category.Kid_Friendly
            ),
            CityItem(
                titleResourceId = R.string.place_14_title,
                subtitleResourceId = R.string.place_14_subtitle,
                latitude = 12.975989,
                longitude = 77.599487,
                openCloseTime = R.string.place_14_time,
                famousFor = R.string.place_14_famous,
                imageResourceId = R.drawable.science_museum,
                cityImageBanner = R.drawable.science_museum_banner,
                cityDetails = R.string.place_14_details,
                category = Category.Kid_Friendly
            ),
            CityItem(
                titleResourceId = R.string.place_15_title,
                subtitleResourceId = R.string.place_15_subtitle,
                latitude = 13.039425,
                longitude = 77.620674,
                openCloseTime = R.string.place_15_time,
                famousFor = R.string.place_15_famous,
                imageResourceId = R.drawable.lumbini_gardens,
                cityImageBanner = R.drawable.lumbini_gardens_banner,
                cityDetails = R.string.place_15_details,
                category = Category.Kid_Friendly
            ),
            CityItem(
                titleResourceId = R.string.place_16_title,
                subtitleResourceId = R.string.place_16_subtitle,
                latitude = 12.9763,
                longitude = 77.5929,
                openCloseTime = R.string.place_16_time,
                famousFor = R.string.place_16_famous,
                imageResourceId = R.drawable.cubbon_park,
                cityImageBanner = R.drawable.cubbon_park_banner,
                cityDetails = R.string.place_16_details,
                category = Category.Parks
            ),
            CityItem(
                titleResourceId = R.string.place_17_title,
                subtitleResourceId = R.string.place_17_subtitle,
                latitude = 12.9507,
                longitude = 77.5848,
                openCloseTime = R.string.place_17_time,
                famousFor = R.string.place_17_famous,
                imageResourceId = R.drawable.lalbagh,
                cityImageBanner = R.drawable.lalbagh_banner,
                cityDetails = R.string.place_17_details,
                category = Category.Parks
            ),
            CityItem(
                titleResourceId = R.string.place_18_title,
                subtitleResourceId = R.string.place_18_subtitle,
                latitude = 13.0323,
                longitude = 77.5546,
                openCloseTime = R.string.place_18_time,
                famousFor = R.string.place_18_famous,
                imageResourceId = R.drawable.jp_park,
                cityImageBanner = R.drawable.jp_park_banner,
                cityDetails = R.string.place_18_details,
                category = Category.Parks
            ),
            CityItem(
                titleResourceId = R.string.place_19_title,
                subtitleResourceId = R.string.place_19_subtitle,
                latitude = 12.8842,
                longitude = 77.5020,
                openCloseTime = R.string.place_19_time,
                famousFor = R.string.place_19_famous,
                imageResourceId = R.drawable.turahalli,
                cityImageBanner = R.drawable.turahalli_banner,
                cityDetails = R.string.place_19_details,
                category = Category.Parks
            ),
            CityItem(
                titleResourceId = R.string.place_20_title,
                subtitleResourceId = R.string.place_20_subtitle,
                latitude = 13.0421,
                longitude = 77.5896,
                openCloseTime = R.string.place_20_time,
                famousFor = R.string.place_20_famous,
                imageResourceId = R.drawable.hebbal_lake,
                cityImageBanner = R.drawable.hebbal_lake_banner,
                cityDetails = R.string.place_20_details,
                category = Category.Parks
            ),
            CityItem(
                titleResourceId = R.string.place_21_title,
                subtitleResourceId = R.string.place_21_subtitle,
                latitude = 12.9352,
                longitude = 77.6101,
                openCloseTime = R.string.place_21_time,
                famousFor = R.string.place_21_famous,
                imageResourceId = R.drawable.phoenix_marketcity,
                cityImageBanner = R.drawable.phoenix_marketcity_banner,
                cityDetails = R.string.place_21_details,
                category = Category.Shopping_Centers
            ),
            CityItem(
                titleResourceId = R.string.place_22_title,
                subtitleResourceId = R.string.place_22_subtitle,
                latitude = 13.0092,
                longitude = 77.5544,
                openCloseTime = R.string.place_22_time,
                famousFor = R.string.place_22_famous,
                imageResourceId = R.drawable.orion_mall,
                cityImageBanner = R.drawable.orion_mall_banner,
                cityDetails = R.string.place_22_details,
                category = Category.Shopping_Centers
            ),
            CityItem(
                titleResourceId = R.string.place_23_title,
                subtitleResourceId = R.string.place_23_subtitle,
                latitude = 12.9743,
                longitude = 77.6075,
                openCloseTime = R.string.place_23_time,
                famousFor = R.string.place_23_famous,
                imageResourceId = R.drawable.garuda_mall,
                cityImageBanner = R.drawable.garuda_mall_banner,
                cityDetails = R.string.place_23_details,
                category = Category.Shopping_Centers
            ),
            CityItem(
                titleResourceId = R.string.place_24_title,
                subtitleResourceId = R.string.place_24_subtitle,
                latitude = 12.9748,
                longitude = 77.6093,
                openCloseTime = R.string.place_24_time,
                famousFor = R.string.place_24_famous,
                imageResourceId = R.drawable.commercial_street,
                cityImageBanner = R.drawable.commercial_street_banner,
                cityDetails = R.string.place_24_details,
                category = Category.Shopping_Centers
            ),
            CityItem(
                titleResourceId = R.string.place_25_title,
                subtitleResourceId = R.string.place_25_subtitle,
                latitude = 12.9716,
                longitude = 77.5934,
                openCloseTime = R.string.place_25_time,
                famousFor = R.string.place_25_famous,
                imageResourceId = R.drawable.ub_city,
                cityImageBanner = R.drawable.ub_city_banner,
                cityDetails = R.string.place_25_details,
                category = Category.Shopping_Centers
            )
        )
    }
}