package com.jorgeromo.androidClassMp1.onboarding

import androidx.annotation.DrawableRes

data class OnboardingPage(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
)
