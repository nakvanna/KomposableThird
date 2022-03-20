package com.cloudwarekh.komposablethird.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.cloudwarekh.komposablethird.util.collectAsStateWhenStarted
import kotlinx.coroutines.flow.map

@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun HomePage() {
    val homeStore = hiltViewModel<HomeStoreViewModel>()
    val list = homeStore.state
        .map { it.list }
        .collectAsStateWhenStarted(initial = emptyList())
        .value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Home Screen") },
                navigationIcon = {
                    IconButton(onClick = {

                    }) {
                        Icon(Icons.Filled.Menu, contentDescription = null)
                    }
                },
            )
        },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {

            LazyColumn {
                items(
                    count = list.size
                ) {
                    Text(text = list[it])
                }
            }
            Button(onClick = {
                homeStore.dispatch(HomeAction.GoToSetting)
            }) {
                Text(text = "To Setting")
            }
        }
    }
}