package com.example.superheroapi.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheroapi.data.SuperHeroApiService
import com.example.superheroapi.databinding.ActivityMainBinding
import com.example.superheroapi.main.DetailSuperHeroActivity.Companion.EXTRA_ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var superHeroApiService: SuperHeroApiService

    private lateinit var adapter: SuperHeroAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initIU()


    }

    private fun initIU() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        adapter = SuperHeroAdapter{id -> navigateToDetail(id)}
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

    }

    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = superHeroApiService.getSuperHeroes(query)
            Log.i("Vane", "It´s successful")
            if (myResponse.isSuccessful) {
                val response = myResponse.body()
                runOnUiThread {
                    adapter.updateList(response?.results ?: emptyList())
                    binding.progressBar.isVisible = false
                }
                if (response != null) {
                    Log.i("Vane", response.toString())
                } else {
                    Log.i("Vane", "No response")
                }

            }
        }
    }

    private fun navigateToDetail(id: String){
        val intent = Intent(this, DetailSuperHeroActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }

}