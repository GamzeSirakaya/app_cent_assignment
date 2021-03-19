package com.example.news.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.adapter.NewsAdapter
import com.example.news.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.recycler_item.*

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private val newsAdapter = NewsAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        viewModel.refreshData()
        news_recycler_view.layoutManager = LinearLayoutManager(context)
        news_recycler_view.adapter = newsAdapter
        observeLiveData()
    }

    fun observeLiveData() {
        viewModel.newResponse.observe(viewLifecycleOwner, Observer { newResponse ->
            newResponse?.let {
                newsAdapter.newListUpdate(newResponse.articles) }

        })

    }
}