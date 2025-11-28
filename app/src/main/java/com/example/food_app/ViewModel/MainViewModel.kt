package com.example.food_app.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.food_app.Domain.CategoryModel
import com.example.food_app.Repository.MainRepository

class MainViewModel : ViewModel() {

    private val repository = MainRepository()

    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
        return repository.loadCategory()
    }
}
