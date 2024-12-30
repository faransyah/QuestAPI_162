package com.example.p12.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.p12.ui.view.DestinasiDetail
import com.example.p12.ui.view.DestinasiEntry
import com.example.p12.ui.view.DestinasiHome
import com.example.p12.ui.view.DestinasiUpdate
import com.example.p12.ui.view.DetailMhsView
import com.example.p12.ui.view.EntryMhsScreen
import com.example.p12.ui.view.HomeScreen
import com.example.p12.ui.view.UpdateView
import java.security.KeyStore.Entry

@Composable
fun PengelolaHalaman(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier,
    ){
        composable(DestinasiHome.route){
            HomeScreen(
                navigateToItemEntry = {navController.navigate(DestinasiEntry.route)},
                onDetailClick = { nim ->
                    navController.navigate("${DestinasiDetail.route}/$nim") {
                        popUpTo(DestinasiHome.route) {
                            inclusive = true
                        }
                    }
                    println("PengelolaHalaman: nim = $nim")

                }
            )
        }
        composable(DestinasiEntry.route) {
            EntryMhsScreen(
                navigateBack = {
                    navController.navigate(DestinasiHome.route) {
                        popUpTo(DestinasiHome.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(DestinasiDetail.routesWithArg) { backStackEntry ->
            val nim = backStackEntry.arguments?.getString(DestinasiDetail.NIM)


            nim?.let {
                DetailMhsView(
                    nim = it,
                    navigateBack = {
                        navController.navigate(DestinasiHome.route) {
                            popUpTo(DestinasiHome.route) {
                                inclusive = true
                            }
                        }
                    },
                    OnEditClick = {
                        navController.navigate("${DestinasiUpdate.route}/$it")
                    }
                )
            }
        }

        // Destinasi Update
        composable(DestinasiUpdate.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdate.NIM) {
                    type = NavType.StringType
                }
            )) { backStackEntry ->
            val nim = backStackEntry.arguments?.getString(DestinasiUpdate.NIM)
            nim?.let {
                UpdateView(
                    navigateBack = {
                        navController.popBackStack()
                    },
                    modifier = modifier
                )
            }
        }

    }
}


