package ru.gmasalskikh.ezcs.screens.ranks

import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.ViewState

data class RanksViewState(
    val name: String = "Ranks",
    override val currentSideEffect: SideEffect = SideEffect.Data
) : ViewState