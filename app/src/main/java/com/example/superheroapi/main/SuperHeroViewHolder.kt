package com.example.superheroapi.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapi.data.response.SuperHeroItemResult
import com.example.superheroapi.databinding.ItemSuperheroBinding

class SuperHeroViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun bind(superHeroItemResult: SuperHeroItemResult){

        binding.tvSuperHeroName.text = superHeroItemResult.name
    }
}

