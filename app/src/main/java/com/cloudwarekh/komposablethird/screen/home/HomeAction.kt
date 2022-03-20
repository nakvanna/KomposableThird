package com.cloudwarekh.komposablethird.screen.home

sealed class HomeAction {
    data class ListAdded(val list: List<String>) : HomeAction()
    object GoToSetting : HomeAction()
}