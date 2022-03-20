package com.cloudwarekh.komposablethird

import android.app.Application
import com.toggl.komposable.scope.DispatcherProvider
import com.toggl.komposable.scope.StoreScopeProvider
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

@HiltAndroidApp
class BaseApplication : Application(), CoroutineScope, StoreScopeProvider {
    @Inject
    @Named("dispatcher")
    lateinit var dispatchersProviders: DispatcherProvider

    override val coroutineContext: CoroutineContext by lazy {
        dispatchersProviders.main
    }

    override fun getStoreScope(): CoroutineScope = this
}