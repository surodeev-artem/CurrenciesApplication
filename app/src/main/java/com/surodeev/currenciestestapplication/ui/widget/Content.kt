package com.surodeev.currenciestestapplication.ui.widget

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.surodeev.currenciestestapplication.entity.CurrenciesState
import com.surodeev.currenciestestapplication.ui.viewmodel.MainViewModel
import com.surodeev.currenciestestapplication.ui.widget.app_bar.FailedAppBar
import com.surodeev.currenciestestapplication.ui.widget.app_bar.InitialAppBar
import com.surodeev.currenciestestapplication.ui.widget.app_bar.SuccessAppBar
import com.surodeev.currenciestestapplication.ui.widget.filter.FilterBottomSheet
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
                    screenState.currenciesState,
                    {
                        viewModel.fetchData()
                    }
                ) {
                    viewModel.clickFavoriteBtn(it)
                }
            },
            bottomBar = {
                BottomNavigationBar(navController)
            }
        )
    }
}