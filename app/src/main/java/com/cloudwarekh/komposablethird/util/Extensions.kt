package com.cloudwarekh.komposablethird.util

import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@Composable
fun <T> Flow<T>.collectAsStateWhenStarted(
    initial: T,
    context: CoroutineContext = EmptyCoroutineContext
): State<T> {
    val lifecycleOwner = LocalLifecycleOwner.current
    val flowLifecycleAware = remember(this, lifecycleOwner) {
        this.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    return flowLifecycleAware.collectAsState(initial, context)
}

fun ComponentActivity.handleBackPressesEmitting(callback: () -> Unit) {
    val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            callback()
        }
    }

    lifecycle.addObserver(
        object : DefaultLifecycleObserver {
            override fun onResume(owner: LifecycleOwner) {
                onBackPressedDispatcher.addCallback(backPressedCallback)
            }

            override fun onPause(owner: LifecycleOwner) {
                backPressedCallback.remove()
            }
        }
    )
}