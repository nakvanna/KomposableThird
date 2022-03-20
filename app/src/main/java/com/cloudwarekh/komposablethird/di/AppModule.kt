package com.cloudwarekh.komposablethird.di

import android.app.Application
import com.cloudwarekh.komposablethird.AppAction
import com.cloudwarekh.komposablethird.AppState
import com.cloudwarekh.komposablethird.navigation.NavigationReducer
import com.cloudwarekh.komposablethird.screen.home.HomeReducer
import com.cloudwarekh.komposablethird.screen.home.HomeState
import com.cloudwarekh.komposablethird.screen.home.HomeSubscription
import com.cloudwarekh.komposablethird.screen.setting.SettingReducer
import com.cloudwarekh.komposablethird.screen.setting.SettingState
import com.cloudwarekh.komposablethird.unwrap
import com.toggl.komposable.architecture.Reducer
import com.toggl.komposable.architecture.Store
import com.toggl.komposable.extensions.combine
import com.toggl.komposable.extensions.createStore
import com.toggl.komposable.extensions.pullback
import com.toggl.komposable.scope.DispatcherProvider
import com.toggl.komposable.scope.StoreScopeProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Named("app_reducer")
    fun provideAppReducer(
        navigationReducer: NavigationReducer,
        homeReducer: HomeReducer,
        settingReducer: SettingReducer
    ): Reducer<AppState, AppAction> =
        combine(
            navigationReducer,
            homeReducer.pullback(
                mapToLocalState = { appState ->
                    HomeState(
                        list = appState.homeList,
                        backStack = appState.backStack
                    )
                },
                mapToLocalAction = AppAction::unwrap,
                mapToGlobalState = { appState, homeState ->
                    appState.copy(
                        homeList = homeState.list,
                        backStack = homeState.backStack
                    )
                },
                mapToGlobalAction = { homeAction ->
                    AppAction.Home(homeAction)
                }
            ),

            settingReducer.pullback(
                mapToLocalState = { appState ->
                    SettingState(
                        title = appState.settingTitle,
                        listItem = appState.listItem,
                        backStack = appState.backStack
                    )
                },
                mapToLocalAction = AppAction::unwrap,
                mapToGlobalState = { appState, settingState ->
                    appState.copy(
                        backStack = settingState.backStack
                    )
                },
                mapToGlobalAction = { settingAction ->
                    AppAction.Setting(settingAction)
                }
            )
        )

    @Provides
    @Singleton
    @Named("dispatcher")
    fun dispatcherProvider(): DispatcherProvider =
        DispatcherProvider(
            io = Dispatchers.IO,
            computation = Dispatchers.Default,
            main = Dispatchers.Main
        )

    @Provides
    @Singleton
    @Named("app_store")
    fun provideAppStore(
        @Named("app_reducer") reducer: Reducer<AppState, AppAction>,
        @Named("dispatcher") dispatcherProvider: DispatcherProvider,
        homeSubscription: HomeSubscription,
        application: Application
    ): Store<AppState, AppAction> =
        createStore(
            initialState = AppState(),
            reducer = reducer,
            subscription = homeSubscription,
            dispatcherProvider = dispatcherProvider,
            storeScopeProvider = application as StoreScopeProvider
        )
}