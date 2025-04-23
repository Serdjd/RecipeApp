package com.example.recipeapp.usecase

import com.example.recipeapp.network.api.MealApi
import com.example.recipeapp.network.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(
    private val mealApi: MealApi
) {

    sealed class Result {
        data class Success(val categories: List<Category>): Result()
        data class Failed(val error: String): Result()
        object Loading: Result()
    }

    suspend fun fetch() = withContext(Dispatchers.IO + NonCancellable) {
        try {
            val categories = mealApi.getCategories().categories
            return@withContext if (categories.isNotEmpty()) {
                Result.Success(categories)
            }
            else {
                Result.Failed("Error during Loading")
            }
        } catch (e: Exception) {
            println("Error_ ${e.message}")
            return@withContext Result.Failed(e.message.toString())
        }
    }

}