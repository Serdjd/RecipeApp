package com.example.recipeapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipeapp.network.model.Category
import com.example.recipeapp.screen.DetailView
import com.example.recipeapp.screen.MealsView
import com.example.recipeapp.util.Screen
import com.example.recipeapp.viewmodel.MainViewModel

@Composable
fun RecipeApp(navController: NavHostController, modifier: Modifier) {
    val viewModel: MainViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route) {
        composable(route = Screen.RecipeScreen.route) {
            MealsView(modifier, viewModel.categoryState.value) {
                navController.currentBackStackEntry?.savedStateHandle?.set("category", it)
                navController.navigate(Screen.DetailScreen.route)
            }
        }
        composable(route = Screen.DetailScreen.route) {
            val category =
                navController.previousBackStackEntry?.savedStateHandle?.get<Category>("category")
                    ?: Category(0, "", "", "")
            DetailView(modifier = modifier, category = category)
        }
    }
}