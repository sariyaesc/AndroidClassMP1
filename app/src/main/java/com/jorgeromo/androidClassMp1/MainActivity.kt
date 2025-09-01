package com.jorgeromo.androidClassMp1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.jorgeromo.androidClassMp1.navigation.TabBarNavigationView
import com.jorgeromo.androidClassMp1.onboarding.OnboardingScreen
import com.jorgeromo.androidClassMp1.onboarding.OnboardingViewModel

class MainActivity : ComponentActivity() {
    private val onboardingViewModel = OnboardingViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var showOnboarding by remember {
                mutableStateOf(!onboardingViewModel.isOnboardingCompleted(this))
            }

            var showLogin by remember { mutableStateOf(false) }

            when {
                showOnboarding -> {
                    OnboardingScreen(
                        onFinish = {
                            onboardingViewModel.saveOnboardingCompleted(this)
                            showOnboarding = false
                            showLogin = true
                        }
                    )
                }
                showLogin -> {
                    com.jorgeromo.androidClassMp1.login.LoginScreen(
                        onLoginSuccess = { showLogin = false }
                    )
                }
                else -> {
                    val navController = rememberNavController()
                    TabBarNavigationView(navController = navController)
                }
            }
        }}}

