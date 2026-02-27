package com.brainx.cmp_base.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.brainx.cmp_base.presentation.screens.main_home.ui.MainHomeScreen
import com.brainx.cmp_base.presentation.screens.main_home.viewmodel.MainHomeScreenViewModel
import com.brainx.utils_extensions.navigation.safeNavToNextScreen
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun AppNavHostGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.MainHome
    ) {

        mainHomeScreenRoute(navController = navController)
    }
}

private fun NavGraphBuilder.mainHomeScreenRoute(
    navController: NavHostController
){
    composable<AppRoutes.MainHome> { backStackEntry ->

        MainHomeScreen(
            onNavigate = {
                when(it){
                    else -> Unit
                }
            }
        )
    }
}
