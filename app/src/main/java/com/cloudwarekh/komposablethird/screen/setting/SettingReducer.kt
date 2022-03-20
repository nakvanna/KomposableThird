package com.cloudwarekh.komposablethird.screen.setting

import com.toggl.komposable.architecture.Effect
import com.toggl.komposable.architecture.Mutable
import com.toggl.komposable.architecture.Reducer
import com.toggl.komposable.extensions.mutateWithoutEffects
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingReducer @Inject constructor() : Reducer<SettingState, SettingAction> {
    override fun reduce(
        state: Mutable<SettingState>,
        action: SettingAction
    ): List<Effect<SettingAction>> {
        return when (action) {
            is SettingAction.SettingTitle -> state.mutateWithoutEffects { copy(title = "Setting") }
            is SettingAction.ListItemChanged -> state.mutateWithoutEffects {
                copy(listItem = action.listItem)
            }
            SettingAction.GoBack -> state.mutateWithoutEffects {
                copy().popBackStack()
            }
        }
    }

}