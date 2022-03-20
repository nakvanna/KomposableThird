package com.cloudwarekh.komposablethird.di

import com.cloudwarekh.komposablethird.AppAction
import com.cloudwarekh.komposablethird.AppState
import com.cloudwarekh.komposablethird.screen.home.HomeAction
import com.cloudwarekh.komposablethird.screen.home.HomeState
import com.cloudwarekh.komposablethird.screen.setting.SettingAction
import com.cloudwarekh.komposablethird.screen.setting.SettingState
import com.toggl.komposable.architecture.Store
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Named

@Module
@InstallIn(ActivityRetainedComponent::class)
object AppViewModelModule {

    @Provides
    @Named("home_store")
    fun provideHomeStore(
        @Named("app_store") appStore: Store<AppState, AppAction>
    ): Store<HomeState, HomeAction> =
        appStore.view(
            mapToLocalState = { appState ->
                HomeState(
                    list = appState.homeList,
                    backStack = appState.backStack
                )
            },
            mapToGlobalAction = { homeAction -> AppAction.Home(homeAction) }
        )

    @Provides
    @Named("setting_store")
    fun provideSettingStore(
        @Named("app_store") appStore: Store<AppState, AppAction>
    ): Store<SettingState, SettingAction> =
        appStore.view(
            mapToLocalState = { appState ->
                SettingState(
                    title = appState.settingTitle,
                    listItem = appState.listItem,
                    backStack = appState.backStack
                )
            },
            mapToGlobalAction = { settingAction -> AppAction.Setting(settingAction) }
        )
}