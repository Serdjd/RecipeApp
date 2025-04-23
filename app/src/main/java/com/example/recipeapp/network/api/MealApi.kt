package com.example.recipeapp.network.api

import com.example.recipeapp.network.model.CategoryList
import com.example.recipeapp.util.API_KEY
import retrofit2.http.GET

interface MealApi {
    @GET("${API_KEY}categories.php")
    suspend fun getCategories(): CategoryList
}