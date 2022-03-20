package com.cloudwarekh.komposablethird.screen.home

import com.cloudwarekh.komposablethird.BackStack
import com.cloudwarekh.komposablethird.BackStackAwareState

data class HomeState(
    val list: List<String>,
    override val backStack: BackStack,
) : BackStackAwareState<HomeState> {
    override fun changeBackStack(backStack: BackStack): HomeState =
        copy(backStack = backStack)
}