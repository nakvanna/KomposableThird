package com.cloudwarekh.komposablethird

import androidx.lifecycle.ViewModel
import com.toggl.komposable.architecture.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class AppStoreViewModel @Inject constructor(
    @Named("app_store") store: Store<AppState, AppAction>
) : ViewModel(), Store<AppState, AppAction> by store