package com.xanthenite.isining.composeapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.xanthenite.isining.view.state.State
import com.xanthenite.isining.view.viewmodel.BaseViewModel

@Composable
fun <S : State , VM : BaseViewModel<S>> VM.collectState() = state.collectAsState()