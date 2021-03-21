package com.example.news.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.adapter.NewsAdapter
import com.example.news.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.recycler_item.*

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private val newsAdapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)

            }

            findNavController().navigate(
                R.id.action_homeFragment_to_newsDetailFragment,
                       bundle
            )
        }
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
      setupRecyclerView()
        observeLiveData()

    }

    fun observeLiveData() {
        viewModel.newResponse.observe(viewLifecycleOwner, Observer { newResponse ->
            newResponse?.let {
                newsAdapter.differ.submitList(newResponse.articles) }

        })
        viewModel.newsError.observe(viewLifecycleOwner, Observer { newsError ->
            newsError?.let {
                if (it) {
                    noResult.visibility = View.VISIBLE
                } else {
                    noResult.visibility = View.GONE

                }
            }

        })
        viewModel.newsLoading.observe(viewLifecycleOwner, Observer { newsLoading ->
            newsLoading?.let {
                if (it) {
                    news_recycler_view.visibility = View.GONE
                    noResult.visibility = View.GONE
                    progress.visibility = View.VISIBLE
                } else {
                    progress.visibility = View.GONE

                }
            }

        })

    }
    private fun setupRecyclerView(){
       news_recycler_view.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}