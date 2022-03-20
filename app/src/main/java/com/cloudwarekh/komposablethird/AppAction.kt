package com.cloudwarekh.komposablethird

import com.cloudwarekh.komposablethird.screen.home.HomeAction
import com.cloudwarekh.komposablethird.screen.setting.SettingAction

sealed class AppAction {
    class Home(override val action: HomeAction) : AppAction(), ActionWrapper<HomeAction>
    class Setting(override val action: SettingAction) : AppAction(), ActionWrapper<SettingAction>

    object BackPressed : AppAction()
}

interface ActionWrapper<WrappedAction> {
    val action: WrappedAction
}

inline fun <From, reified To> From.unwrap(): To? =
    if (this !is ActionWrapper<*> || this.action !is To) null
    else this.action as To