package com.surodeev.currenciestestapplication.ui.widget

import android.util.Log
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.surodeev.currenciestestapplication.entity.BottomNavigationItem
import com.surodeev.currenciestestapplication.entity.CurrenciesState
import com.surodeev.currenciestestapplication.entity.Currency
import com.surodeev.currenciestestapplication.ui.viewmodel.FilterViewModel
import com.surodeev.currenciestestapplication.ui.viewmodel.MainViewModel
import com.surodeev.currenciestestapplication.ui.widget.app_bar.FailedAppBar
import com.surodeev.currenciestestapplication.ui.widget.app_bar.InitialAppBar
import com.surodeev.currenciestestapplication.ui.widget.app_bar.SuccessAppBar
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun Content() {
    val viewModel: MainViewModel = viewModel()
    val navController = rememberAnimatedNavController()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.Expanded }
    )
    val coroutineScope = rememberCoroutineScope()
    val screenState = viewModel.screenState

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            FilterBottomSheet(viewModel.sortVariant) { sortVariant ->
                viewModel.sortVariant = sortVariant
            }
        },
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                TopAppBar {
                    when (screenState.currenciesState) {
                        is CurrenciesState.Initial -> InitialAppBar()

                        is CurrenciesState.Success -> SuccessAppBar(
                            currencies = screenState.currenciesState.currencies,
                            baseCurrencyCode = screenState.baseCurrencyCode,
                            rowState = viewModel.baseRatesListState,
                            baseCurrencyClick = {
                                viewModel.changeBaseCurrency(it)
                            }
                        ) {
                            coroutineScope.launch {
                                sheetState.show()
                            }
                        }

                        is CurrenciesState.Failed -> FailedAppBar()
                    }
                }
            },
            content = { innerPadding ->
                AppNavigation(
                    innerPadding,
                    navController,
                    refreshing = screenState.currenciesState is CurrenciesState.Success && screenState.loading,
                    screenState.currenciesState
                ) {
                    viewModel.fetchData()
                }
            },
            bottomBar = {
                BottomNavigationBar(navController)
            }
        )
    }
}