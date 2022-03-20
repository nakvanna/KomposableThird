package com.cloudwarekh.komposablethird

import com.cloudwarekh.komposablethird.navigation.AppDestination
import com.toggl.komposable.architecture.Effect
import com.toggl.komposable.architecture.Mutable
import com.toggl.komposable.extensions.mutateWithoutEffects

data class AppState(
    val homeList: List<String> = emptyList(),
    val listItem: String = "",
    val settingTitle: String = "Setting Title",
    override val backStack: BackStack = listOf(AppDestination.Home),
) : BackStackAwareState<AppState> {
    override fun changeBackStack(backStack: BackStack): AppState =
        copy(backStack = backStack)
}

typealias BackStack = List<AppDestination>

fun BackStack.push(destination: AppDestination): List<AppDestination> {
    return this + destination
}

fun BackStack.pop(): List<AppDestination> {
    return this.dropLast(1)
}


interface BackStackAwareState<T> {
    val backStack: BackStack
    fun popBackStack(): T = changeBackStack(backStack.pop())
    fun changeBackStack(backStack: BackStack): T
}

fun <State : BackStackAwareState<State>, Action> Mutable<State>.popBackStackWithoutEffects(): List<Effect<Action>> =
    mutateWithoutEffects { popBackStack() }

