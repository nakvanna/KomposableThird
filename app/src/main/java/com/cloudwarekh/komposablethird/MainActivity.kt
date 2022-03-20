package com.cloudwarekh.komposablethird

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.cloudwarekh.komposablethird.navigation.AppDestination
import com.cloudwarekh.komposablethird.navigation.AppNavigationHost
import com.cloudwarekh.komposablethird.screen.ui.theme.KomposableThirdTheme
import com.cloudwarekh.komposablethird.util.collectAsStateWhenStarted
import com.cloudwarekh.komposablethird.util.handleBackPressesEmitting
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
@SuppressLint("FlowOperatorInvokedInComposition")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val appStore = hiltViewModel<AppStoreViewModel>()
            val backStack by appStore.state
                .map { it.backStack }
                .collectAsStateWhenStarted(initial = listOf(AppDestination.Home))

            KomposableThirdTheme {
                this.handleBackPressesEmitting {
                    if (backStack.size == 1) {
                        this.finish()
                    } else {
                        appStore.dispatch(AppAction.BackPressed)
                    }
                }
                AppNavigationHost(backStack)
            }
        }
    }
}



