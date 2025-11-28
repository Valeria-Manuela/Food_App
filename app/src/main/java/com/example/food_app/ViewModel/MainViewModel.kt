// MainViewModel.kt
package com.example.food_app.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.food_app.Domain.CategoryModel
import com.example.food_app.Repository.MainRepository

class MainViewModel(
    private val repository: MainRepository = MainRepository() // injeção simples
) : ViewModel() {


    val categoryLiveData: LiveData<List<CategoryModel>> = repository.categories

}
