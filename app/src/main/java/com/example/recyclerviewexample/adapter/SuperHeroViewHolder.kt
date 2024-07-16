package com.example.recyclerviewexample.adapter

import android.view.View
// import android.widget.Toast
//import android.widget.ImageView
//import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
//import com.example.recyclerviewexample.R
import com.example.recyclerviewexample.SuperHero
import com.example.recyclerviewexample.databinding.ItemSuperheroBinding

class SuperHeroViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    // private val superHero = view.findViewById<TextView>(R.id.tvSuperHeroName)
    // private val realName = view.findViewById<TextView>(R.id.tvRealName)
    // private val publisher = view.findViewById<TextView>(R.id.tvPublisher)
    // private val photo = view.findViewById<ImageView>(R.id.ivSuperHero)

    fun render(
        superHeroModel: SuperHero,
        onClickListener: (SuperHero) -> Unit,
        onClickDelete: (Int) -> Unit
    ) {

        // superHero.text = superHeroModel.superHero
        // realName.text = superHeroModel.realName
        // publisher.text = superHeroModel.publisher
        // Glide.with(photo.context).load(superHeroModel.photo).into(photo)
        binding.tvSuperHeroName.text = superHeroModel.superHero
        binding.tvRealName.text = superHeroModel.realName
        binding.tvPublisher.text = superHeroModel.publisher
        Glide.with(binding.ivSuperHero.context).load(superHeroModel.photo).into(binding.ivSuperHero)

        //binding.ivSuperHero.setOnClickListener {
        //    Toast.makeText(
        //        binding.ivSuperHero.context,
        //        superHeroModel.realName,
        //        Toast.LENGTH_SHORT
        //    ).show()
        //}

        itemView.setOnClickListener {
            //Toast.makeText(
            //    binding.ivSuperHero.context,
            //    superHeroModel.superHero,
            //    Toast.LENGTH_SHORT
            //).show()
            onClickListener(superHeroModel)
        }

        binding.btnDelete.setOnClickListener { onClickDelete(adapterPosition) }
    }
}