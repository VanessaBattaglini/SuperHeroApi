package com.example.superheroapi.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapi.data.response.SuperHeroItemResult
import com.example.superheroapi.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun bind(superHeroItemResult: SuperHeroItemResult, onItemSelected: (String) -> Unit){

        binding.tvSuperHeroName.text = superHeroItemResult.name
        Picasso.get().load(superHeroItemResult.image.url).into(binding.ivSuperHero)
        binding.root.setOnClickListener{onItemSelected(superHeroItemResult.id)}
    }
}

