// Repository.kt
package com.example.food_app.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.food_app.Domain.CategoryModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import android.util.Log

class MainRepository {


    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val categoryRef = firebaseDatabase.getReference("Category")

    private val _categories = MutableLiveData<List<CategoryModel>>()
    val categories: LiveData<List<CategoryModel>> get() = _categories

    init {
        loadCategories()
    }

    private fun loadCategories() {
        categoryRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<CategoryModel>()
                for (child in snapshot.children) {
                    child.getValue(CategoryModel::class.java)?.let { list.add(it) }
                }
                _categories.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MainRepository", "Erro ao carregar categorias: ${error.message}", error.toException())
                _categories.value = emptyList()
            }
        })
    }

}
