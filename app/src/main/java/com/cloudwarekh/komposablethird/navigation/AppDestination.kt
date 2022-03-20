package com.cloudwarekh.komposablethird.navigation

sealed class AppDestination(val route: String) {
    object Home : AppDestination("home")
    object Setting : AppDestination("setting")
    object About : AppDestination("about")
    object Test : AppDestination("test")
}