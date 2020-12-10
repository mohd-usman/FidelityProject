package com.example.fideltyproject.activities

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fideltyproject.R
import com.example.fideltyproject.adapter.AnimeAdapter
import com.example.fideltyproject.model.SearchAnimeResponse


class MainActivity : AppCompatActivity() {


    private var anime: MutableList<SearchAnimeResponse.SearchAnimeItem?>? = mutableListOf()
    private  lateinit var  animeAdapter : AnimeAdapter

    private val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animeAdapter= AnimeAdapter(anime,this)

        findViewById<RecyclerView>(R.id.recyclerView).run {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = animeAdapter
        }

        viewModel.articleList.observe(this, Observer {

            anime= it as MutableList<SearchAnimeResponse.SearchAnimeItem?>?
            setUpListView(it)
        })
        viewModel.errorMessage.observe(this, Observer {

            Log.d("error loading api",it)
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }
    private fun setUpListView(it: List<SearchAnimeResponse.SearchAnimeItem?>?) {
        it?.let { animeResponse ->
            anime?.apply {

//                clear()
//                addAll(animeResponse)

            }
//            animeAdapter.notifyDataSetChanged()

        }
        animeAdapter.setData(it as MutableList<SearchAnimeResponse.SearchAnimeItem?>?)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.menu_search, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        val mSearchView = menu.findItem(R.id.menu_search).actionView as androidx.appcompat.widget.SearchView
        if (mSearchView != null) {
            mSearchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
            val queryTextListener = object :
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override
                fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null && newText.length > 0) {
                        val mSearchText = newText
                        val list= anime?.filter {
                            it?.title!!.startsWith(mSearchText) }

                        setUpListView(list)

                    }else {
                        setUpListView(anime)
                    }
                    return true
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }
            }
//            mSearchView.setOnQueryTextListener(queryTextListener)
            mSearchView.setOnQueryTextListener(queryTextListener)
        }
        return true
    }

}