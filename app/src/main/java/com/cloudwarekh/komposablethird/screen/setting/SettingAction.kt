package com.cloudwarekh.komposablethird.screen.setting

sealed class SettingAction {
    data class SettingTitle(val title: String) : SettingAction()
    data class ListItemChanged(val listItem: String) : SettingAction()
    object GoBack : SettingAction()
}