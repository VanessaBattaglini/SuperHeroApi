package com.example.superheroapi.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.superheroapi.data.SuperHeroApiService
import com.example.superheroapi.data.response.SuperHeroDetailResponse
import com.example.superheroapi.databinding.ActivityDetailSuperHeroBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailSuperHeroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSuperHeroBinding

    @Inject
    lateinit var superHeroApiService: SuperHeroApiService


    companion object{
        const val EXTRA_ID = "extra_id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getInformationSuperHero(id)
    }

    private fun getInformationSuperHero(id: String) {
        CoroutineScope(Dispatchers.IO).launch{
            val responseDetail = superHeroApiService.getSuperHeroDetail(id)
            if(responseDetail.body() != null){
               runOnUiThread{createUI(responseDetail.body()!!)
                }
            }
        }

    }

    private fun createUI(superHero: SuperHeroDetailResponse) {
        Picasso.get().load(superHero.image.url).into(binding.ivSuperHero)
        binding.tvSuperHeroName.text = superHero.name
    }


}