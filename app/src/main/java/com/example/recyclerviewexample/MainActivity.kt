package com.example.recyclerviewexample

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
// import androidx.recyclerview.widget.DividerItemDecoration
// import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
// import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewexample.adapter.SuperHeroAdapter
import com.example.recyclerviewexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var superHeroMutableList: MutableList<SuperHero> = SuperHeroProvider.superHeroList.toMutableList()
    private lateinit var adapter: SuperHeroAdapter
    private var linearLayout = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnAddSuperHero.setOnClickListener { createSuperHero() }
        //binding.btnAddSuperHero2.setOnClickListener { createSuperHero() }

        configFilter()
        initRecyclerView()
        configSwipe()
    }

    private fun configSwipe() {
        binding.srLayout.setColorSchemeResources(R.color.red, R.color.blue, R.color.orange, R.color.green)
        binding.srLayout.setProgressBackgroundColorSchemeResource(R.color.yellow)

        binding.srLayout.setOnRefreshListener {
            Log.i("fulanoSwipe", "It work")

            Handler(Looper.getMainLooper()).postDelayed({
                binding.srLayout.isRefreshing = false
            }, 4000)
        }
    }

    private fun configFilter() {

        binding.etFilter.addTextChangedListener { userFilter ->
            // Log.i("fulano", it.toString())
            val superHeroFiltered = superHeroMutableList.filter { superHero -> superHero.superHero.lowercase().contains(userFilter.toString().lowercase()) }
            adapter.updateSuperHeroes(superHeroFiltered)
        }
    }

    private fun createSuperHero() {
        val superHero = SuperHero(
            "Disguise",
            "FulDevsCorporation",
            "???????",
            "https://media.gettyimages.com/id/1451456915/de/foto/weibliche-freiberufliche-entwicklerin-f%C3%BCr-programmierung-und-programmierung-codierung-auf-zwei.jpg?s=2048x2048&w=gi&k=20&c=mJG3cyi8aRnJ_PYuZ9oAIrTbeFCaHOrbmlBFXcO_5Ks="
        )

        //superHeroMutableList.add(index = 3, superHero)
        superHeroMutableList.add(superHero)
        adapter.notifyItemInserted(superHeroMutableList.size - 1)
        linearLayout.scrollToPositionWithOffset(superHeroMutableList.size -1, 20)
    }

    private fun initRecyclerView() {

        adapter = SuperHeroAdapter(
            superheroList =  superHeroMutableList,
            onClickListener =  { superhero -> onItemSelected(superhero) },
            onClickDelete = {position -> onDeletedItem(position)}
        )

        //val manager = LinearLayoutManager(this)
        //val manager = GridLayoutManager(this, 2)
        // val decoration = DividerItemDecoration(this, manager.orientation)
        // val recyclerView = findViewById<RecyclerView>(R.id.recyclerSuperHero)
        // recyclerView.layoutManager = LinearLayoutManager(this)
        // recyclerView.adapter = SuperHeroAdapter(SuperHeroProvider.superHeroList)
        binding.recyclerSuperHero.layoutManager = linearLayout
        //binding.recyclerSuperHero.adapter = SuperHeroAdapter(SuperHeroProvider.superHeroList, {onItemSelected(it)})
        // other way
        // binding.recyclerSuperHero.adapter = SuperHeroAdapter(SuperHeroProvider.superHeroList, {superhero -> onItemSelected(superhero)})

        //binding.recyclerSuperHero.adapter = SuperHeroAdapter(SuperHeroProvider.superHeroList) { superhero ->
        //    onItemSelected(
        //        superhero
        //    )
        //}
        binding.recyclerSuperHero.adapter = adapter

        // binding.recyclerSuperHero.addItemDecoration(decoration)
    }

    private fun onDeletedItem(position: Int) {
        superHeroMutableList.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

    private fun onItemSelected(superHero: SuperHero) {
        Toast.makeText(this, superHero.superHero, Toast.LENGTH_SHORT).show()
    }
}