package com.example.superheroapi.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapi.R
import com.example.superheroapi.data.response.SuperHeroItemResult

class SuperHeroAdapter(
    var superHeroList: List<SuperHeroItemResult> = emptyList()
): RecyclerView.Adapter<SuperHeroViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(superHeroList: List<SuperHeroItemResult>){
        this.superHeroList = superHeroList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }

    override fun onBindViewHolder(
        viewholder: SuperHeroViewHolder,
        position: Int
    ) {
        viewholder.bind(superHeroList[position])
    }

    override fun getItemCount(): Int = superHeroList.size

}