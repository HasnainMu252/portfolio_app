package com.portfolio_app

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import api_interface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var RecyclerRepo : RecyclerView
    private lateinit var adapter: repo_Adapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)



        RecyclerRepo = findViewById(R.id.Repolist)

        // Set the LayoutManager
        RecyclerRepo.layoutManager = LinearLayoutManager(this)

        adapter = repo_Adapter(this, emptyList())
        RecyclerRepo.adapter = adapter


       fatchRepo()
    }

    private fun fatchRepo() {


        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())

            .build()

        val service = retrofit.create(api_interface::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val token = "" // Your token
                val repositories = service.getRepositories(token)

                // Switch to the Main thread to update UI
                withContext(Dispatchers.Main) {

                        adapter = repo_Adapter(this@MainActivity, repositories)
                    RecyclerRepo.adapter = adapter

                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    // Handle API errors such as token issues
                    Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    }





