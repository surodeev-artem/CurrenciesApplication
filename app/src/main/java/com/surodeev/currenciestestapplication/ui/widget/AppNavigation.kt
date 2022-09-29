package com.surodeev.currenciestestapplication.ui.widget

import android.util.Log
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.surodeev.currenciestestapplication.entity.BottomNavigationItem
import com.surodeev.currenciestestapplication.entity.CurrenciesState
import com.surodeev.currenciestestapplication.entity.Currency
import com.surodeev.currenciestestapplication.ui.widget.currencies_base.FailedCurrenciesBase
import com.surodeev.currenciestestapplication.ui.widget.currencies_base.InitialCurrenciesBase
import com.surodeev.currenciestestapplication.ui.widget.currencies_base.SuccessCurrenciesBase

@ExperimentalAnimationApi
@Composable
fun AppNavigation(
    innerPadding: PaddingValues,
    navController: NavHostController,
    refreshing: Boolean,
    currenciesState: CurrenciesState,
    refreshRates: () -> Unit,
    clickFavoriteBtn: (Currency) -> Unit
) {
    val animationDuration = 300

    AnimatedNavHost(
        navController,
        startDestination = BottomNavigationItem.Popular.route,
        Modifier.padding(innerPadding)
    ) {
        composable(
            route = BottomNavigationItem.Popular.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(animationDuration)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(animationDuration)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(animationDuration)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(animationDuration)
                )
            }
        ) {
            SwipeRefresh(
                state = SwipeRefreshState(refreshing),
                onRefresh = {
                    refreshRates()
                }
            ) {
                when (currenciesState) {
                    is CurrenciesState.Success -> {
                        SuccessCurrenciesBase(currenciesState) {
                            clickFavoriteBtn(it)
                        }
                    }

                    is CurrenciesState.Initial -> {
                        InitialCurrenciesBase()
                    }

                    is CurrenciesState.Failed -> {
                        FailedCurrenciesBase {
                            refreshRates()
                        }
                    }
                }
            }
        }
        composable(
            route = BottomNavigationItem.Favorites.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(animationDuration)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(animationDuration)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(animationDuration)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(animationDuration)
                )
            }
        ) {
            when (currenciesState) {
                is CurrenciesState.Success -> {
                    SuccessCurrenciesBase(currenciesState, true) {
                        clickFavoriteBtn(it)
                    }
                }

                is CurrenciesState.Initial -> {
                    InitialCurrenciesBase()
                }

                is CurrenciesState.Failed -> {
                    FailedCurrenciesBase {
                        refreshRates()
                    }
                }
            }
        }
    }
}