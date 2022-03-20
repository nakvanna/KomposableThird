package com.cloudwarekh.komposablethird.screen.home

import com.cloudwarekh.komposablethird.navigation.AppDestination
import com.cloudwarekh.komposablethird.push
import com.toggl.komposable.architecture.Effect
import com.toggl.komposable.architecture.Mutable
import com.toggl.komposable.architecture.Reducer
import com.toggl.komposable.extensions.mutateWithoutEffects
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeReducer @Inject constructor() : Reducer<HomeState, HomeAction> {
    override fun reduce(state: Mutable<HomeState>, action: HomeAction): List<Effect<HomeAction>> =
        when (action) {
            is HomeAction.ListAdded -> state.mutateWithoutEffects { copy(list = action.list) }
            HomeAction.GoToSetting -> state.mutateWithoutEffects {
                copy(backStack = backStack.push(AppDestination.Setting))
            }
        }
}