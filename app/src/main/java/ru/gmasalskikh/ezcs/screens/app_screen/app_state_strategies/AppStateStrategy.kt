package ru.gmasalskikh.ezcs.screens.app_screen.app_state_strategies

import androidx.annotation.StringRes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.screens.app_screen.AppState
import java.lang.IllegalStateException

abstract class AppStateStrategy {

    protected abstract val appState: AppState
    protected open val cs: CoroutineScope? = null
    protected open val navEventEmitter: FlowCollector<TargetNavigation>? = null
    abstract fun applyStrategy(): AppState

    protected fun navigateTo(targetNavigation: TargetNavigation) = cs?.launch {
        navEventEmitter?.emit(targetNavigation)
    }

    protected fun getAppStateWithNewTopBarTitle(@StringRes titleRes: Int) =
        (appState.appTopBarState as? AppState.AppTopBarState.AppTopBar)?.let { appTopBar ->
            appState.copy(
                appTopBarState = appTopBar.copy(
                    titleRes = titleRes
                )
            )
        } ?: throw IllegalStateException("Current screen no has AppTopBar")

}