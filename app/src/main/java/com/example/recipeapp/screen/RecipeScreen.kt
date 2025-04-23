package com.example.recipeapp.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.recipeapp.network.model.Category
import com.example.recipeapp.ui.theme.RecipeAppTheme
import com.example.recipeapp.usecase.GetCategoryUseCase

@Composable
fun MealsView(
    modifier: Modifier = Modifier,
    state: GetCategoryUseCase.Result,
    navigate: (Category) -> Unit
) {
    Box(
        modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues())
    ) {
        when (state) {
            is GetCategoryUseCase.Result.Success -> {
                MealCategoriesList(
                    categories = state.categories,
                    navigate = navigate
                )
            }

            is GetCategoryUseCase.Result.Loading -> {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }

            is GetCategoryUseCase.Result.Failed -> {
                Text(text = "Error Occurred", modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
fun MealCategoriesList(
    categories: List<Category>,
    navigate: (Category) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), Modifier.fillMaxSize()
    ) {
        items(categories, key = { it.id }) { category ->
            Category(
                category,
                navigate = navigate
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Category(category: Category, navigate: (Category) -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable {
                navigate(category)
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlideImage(
            model = category.imageUrl,
            contentDescription = "Image",
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )
        Text(
            text = category.name,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MealCategoriesListPreview() {
    RecipeAppTheme {
        MealsView(
            state = GetCategoryUseCase.Result.Loading, navigate = {})
    }
}