package com.cloudwarekh.komposablethird.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cloudwarekh.komposablethird.screen.about.AboutPage
import com.cloudwarekh.komposablethird.screen.home.HomePage
import com.cloudwarekh.komposablethird.screen.setting.SettingPage

@Composable
fun AppNavigationHost(backStack: List<AppDestination>) {
    val navController = rememberNavController()

    // extremely naive implementation for your purposes
    if (navController.currentDestination != null) {
        navController.navigate(backStack.last().route)
    }

    NavHost(
        navController = navController,
        startDestination = AppDestination.Home.route
    ) {
        composable(AppDestination.Home.route) { HomePage() }
        composable(AppDestination.Setting.route) { SettingPage() }
        composable(AppDestination.About.route) { AboutPage() }
    }
}
