package com.example.recipeapp.util

sealed class Screen(val route: String) {
    object RecipeScreen: Screen("recipe_screen")
    object DetailScreen: Screen("detail_screen")
}