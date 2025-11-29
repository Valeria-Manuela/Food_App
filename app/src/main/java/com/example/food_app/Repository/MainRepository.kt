package com.example.food_app.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.food_app.Domain.CategoryModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.Locale
import android.util.Log
import com.example.food_app.Domain.FoodModel
import com.google.firebase.database.Query

class MainRepository {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun loadCategory(): LiveData<MutableList<CategoryModel>> {

        val listData = MutableLiveData<MutableList<CategoryModel>>()

        val ref = firebaseDatabase.getReference("Category")

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val list = mutableListOf<CategoryModel>()

                for (childSnapshot in snapshot.children) {

                    val item = childSnapshot.getValue(CategoryModel::class.java)

                    item?.let { list.add(it) }
                }

                listData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(
                    "MainRepository",
                    "Erro ao carregar categorias do Firebase: ${error.message}",
                    error.toException()
                )
            }
        })

        return listData
    }

    fun loadBestFood(): LiveData<MutableList<FoodModel>> {

        val listData = MutableLiveData<MutableList<FoodModel>>()
        val ref = firebaseDatabase.getReference("Foods")

        val query = ref.orderByChild("BestFood").equalTo(true)

        query.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val lists = mutableListOf<FoodModel>()

                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(FoodModel::class.java)
                    item?.let { lists.add(it) }
                }

                listData.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MainRepository", "Erro ao carregar BestFood: ${error.message}")
            }
        })

        return listData
    }

    fun loadFiltered(id: String): LiveData<MutableList<FoodModel>> {
        val listData = MutableLiveData<MutableList<FoodModel>>()
        val ref = firebaseDatabase.getReference("Foods")
        val query: Query = ref.orderByChild("CategoryId").equalTo(id)

        query.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {


                val lists = mutableListOf<FoodModel>()

                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(FoodModel::class.java)
                    item?.let { lists.add(it) }
                }

                listData.value = lists

            }

            override fun onCancelled(error: DatabaseError) {}
        })

        return listData

    }

}