package com.example.example13_superhero.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.example13_superhero.R

data class HeroSource(@StringRes val nameRes:Int, @StringRes val descriptionRes:Int,@DrawableRes val imageRes:Int)

val heroes = listOf(
    HeroSource(nameRes = R.string.hero1, descriptionRes = R.string.description1, imageRes = R.drawable.android_superhero1),
    HeroSource(nameRes = R.string.hero2, descriptionRes = R.string.description2, imageRes = R.drawable.android_superhero2),
    HeroSource(nameRes = R.string.hero3, descriptionRes = R.string.description3, imageRes = R.drawable.android_superhero3),
    HeroSource(nameRes = R.string.hero4, descriptionRes = R.string.description4, imageRes = R.drawable.android_superhero4),
    HeroSource(nameRes = R.string.hero5, descriptionRes = R.string.description5, imageRes = R.drawable.android_superhero5),
    HeroSource(nameRes = R.string.hero6, descriptionRes = R.string.description6, imageRes = R.drawable.android_superhero6),
)
