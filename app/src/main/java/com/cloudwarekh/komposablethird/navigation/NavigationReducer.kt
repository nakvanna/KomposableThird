package com.cloudwarekh.komposablethird.navigation

import com.cloudwarekh.komposablethird.AppAction
import com.cloudwarekh.komposablethird.AppState
import com.cloudwarekh.komposablethird.popBackStackWithoutEffects
import com.toggl.komposable.architecture.Effect
import com.toggl.komposable.architecture.Mutable
import com.toggl.komposable.architecture.Reducer
import com.toggl.komposable.extensions.noEffect
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NavigationReducer @Inject constructor() : Reducer<AppState, AppAction> {
    override fun reduce(state: Mutable<AppState>, action: AppAction): List<Effect<AppAction>> {
        return when (action) {
            AppAction.BackPressed -> state.popBackStackWithoutEffects()
            else -> noEffect()
        }
    }

}
