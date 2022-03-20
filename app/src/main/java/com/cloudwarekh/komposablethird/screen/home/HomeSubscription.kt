package com.cloudwarekh.komposablethird.screen.home

import com.cloudwarekh.komposablethird.AppAction
import com.cloudwarekh.komposablethird.AppState
import com.toggl.komposable.architecture.Subscription
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeSubscription @Inject constructor() : Subscription<AppState, AppAction> {
    override fun subscribe(state: Flow<AppState>): Flow<AppAction> {
        return flowOf(AppAction.Home(HomeAction.ListAdded(listOf("Hello", "World", "Coding"))))
    }
}