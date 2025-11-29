package com.example.food_app.Helper

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken

import android.content.Context
import android.widget.Toast
import com.example.food_app.Domain.FoodModel
import com.google.gson.Gson
import androidx.core.content.edit

class ManagmentCart(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences("Cart", Context.MODE_PRIVATE)
    private val gson = Gson()

    private fun getListCart(): ArrayList<FoodModel> {
        val json = sharedPreferences.getString("CART_LIST", null)
        if (json.isNullOrEmpty()) return ArrayList()
        return try {
            val type = object : TypeToken<ArrayList<FoodModel>>() {}.type
            gson.fromJson(json, type) ?: ArrayList()
        } catch (e: Exception) {
            e.printStackTrace()
            ArrayList()
        }
    }


    private fun saveList(list: ArrayList<FoodModel>) {
        sharedPreferences.edit {
            val json = gson.toJson(list)
            putString("CART_LIST", json)
        }
    }

    fun insertItem(item: FoodModel) {
        val list = getListCart()
        var existAlready = false
        var index = 0

        for (i in list.indices) {
            if (list[i].Title == item.Title) {
                existAlready = true
                index = i
                break
            }
        }

        if (existAlready) {
            list[index].numberInCart += item.numberInCart
        } else {
            list.add(item)
        }

        saveList(list)
        Toast.makeText(context, "Adicionado ao carrinho!", Toast.LENGTH_SHORT).show()
    }

    fun getTotalItems(): Int {
        val list = getListCart()
        var total = 0
        for (item in list) {
            total += item.numberInCart
        }
        return total
    }

    fun getCartList(): ArrayList<FoodModel> = getListCart()
}
