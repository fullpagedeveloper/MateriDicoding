package com.fullpagedeveloper.githubuserapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fullpagedeveloper.githubuserapp.model.Users
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_users.*
import java.io.IOException
import java.io.InputStream


class UsersActivity : AppCompatActivity() {
    private val users = "users"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        val listItemUsers = createListFromRawAsset()
        val menuAdapter = UsersAdapter({ itemList -> doClick(itemList) }, listItemUsers)
        recyclerView_Id.adapter = menuAdapter
        menuAdapter.notifyDataSetChanged()
    }

    private fun createListFromRawAsset(): List<Users> {
        val menuJson = resources.openRawResource(R.raw.githubuser).bufferedReader().use { it.readText() }
//        //val myModel: Users = Gson().fromJson(menuJson, Users::class.java)
//        val json: JsonElement = Gson().fromJson(menuJson, JsonElement::class.java)

        val listType = object : TypeToken<List<Users?>?>() {}.type
        return Gson().fromJson(menuJson.asJsonObject[users].asJsonArray, listType)
    }

    /*private fun readInputStreamToString(inputStream: InputStream): String?{
        return try {
            val byte = ByteArray(inputStream.available())
            inputStream.read(byte, 0, byte.size)
            String(byte)
        } catch (e: IOException){
            null
        }

    }*/

    private fun doClick(itemList: Users) {

    }
}