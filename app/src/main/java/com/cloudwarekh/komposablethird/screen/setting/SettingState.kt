package com.cloudwarekh.komposablethird.screen.setting

import com.cloudwarekh.komposablethird.BackStack
import com.cloudwarekh.komposablethird.BackStackAwareState

data class SettingState(
    val title: String,
    val listItem: String,
    override val backStack: BackStack,
) : BackStackAwareState<SettingState> {
    override fun changeBackStack(backStack: BackStack): SettingState =
        copy(backStack = backStack)
}