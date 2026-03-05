package com.example.superheroapi.main

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.superheroapi.data.SuperHeroApiService
import com.example.superheroapi.data.response.PowerStatsResponse
import com.example.superheroapi.data.response.SuperHeroDetailResponse
import com.example.superheroapi.databinding.ActivityDetailSuperHeroBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

@AndroidEntryPoint
class DetailSuperHeroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSuperHeroBinding

    @Inject
    lateinit var superHeroApiService: SuperHeroApiService


    companion object {
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
        CoroutineScope(Dispatchers.IO).launch {
            val responseDetail = superHeroApiService.getSuperHeroDetail(id)
            if (responseDetail.body() != null) {
                runOnUiThread {
                    createUI(responseDetail.body()!!)
                }
            }
        }
    }

    private fun createUI(superHero: SuperHeroDetailResponse) {
        Picasso.get().load(superHero.image.url).into(binding.ivSuperHero)
        binding.tvSuperHeroName.text = superHero.name
        prepareStats(superHero.powerStarts)
        binding.tvSuperHeroFullName.text = superHero.biography.fullName
        binding.tvSuperHeroPublisher.text = superHero.biography.publisher
        binding.tvSuperHeroBase.text = superHero.work.base

    }

    private fun prepareStats(powerStats: PowerStatsResponse) {
        updatedHeight(binding.viewIntelligence, powerStats.intelligence)
        updatedHeight(binding.viewStrength, powerStats.strength)
        updatedHeight(binding.viewSpeed, powerStats.speed)
        updatedHeight(binding.viewDurability, powerStats.durability)
        updatedHeight(binding.viewPower, powerStats.power)
        updatedHeight(binding.viewCombat, powerStats.combat)
    }

    private fun updatedHeight(view: View, stat: String) {
        val params = view.layoutParams
        params.height = pxToDp(stat.toFloat())
        view.layoutParams = params
    }

    private fun pxToDp(px: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics)
            .roundToInt()
    }


}