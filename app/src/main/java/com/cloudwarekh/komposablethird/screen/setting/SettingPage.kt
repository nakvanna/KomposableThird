package com.cloudwarekh.komposablethird.screen.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SettingPage() {
    val settingStore = hiltViewModel<SettingStoreViewModel>()

    Scaffold {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {

                }
            )
            Button(onClick = {
                settingStore.dispatch(SettingAction.GoBack)
            }) {
                Text(text = "Add To List")
            }
        }
    }
}