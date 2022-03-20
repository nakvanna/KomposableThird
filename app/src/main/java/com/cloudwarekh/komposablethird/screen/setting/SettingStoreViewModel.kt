package com.cloudwarekh.komposablethird.screen.setting

import androidx.lifecycle.ViewModel
import com.toggl.komposable.architecture.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SettingStoreViewModel @Inject constructor(
    @Named("setting_store") settingStore: Store<SettingState, SettingAction>
) : ViewModel(), Store<SettingState, SettingAction> by settingStore