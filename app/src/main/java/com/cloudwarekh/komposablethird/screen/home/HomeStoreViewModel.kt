package com.cloudwarekh.komposablethird.screen.home

import androidx.lifecycle.ViewModel
import com.toggl.komposable.architecture.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeStoreViewModel @Inject constructor(
    @Named("home_store") homeStore: Store<HomeState, HomeAction>
) : ViewModel(), Store<HomeState, HomeAction> by homeStore