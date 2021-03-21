package com.example.news.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.webkit.WebViewClient
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.adapter.NewsAdapter
import com.example.news.viewModel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_news_detail.*
import kotlinx.android.synthetic.main.fragment_news_detail.title
import kotlinx.android.synthetic.main.recycler_item.*
import kotlinx.android.synthetic.main.recycler_item.view.*


class NewsDetailFragment : Fragment(R.layout.fragment_news_detail) {
    lateinit var viewModel:HomeViewModel
    val args: NewsDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //   viewModel = (activity as HomeFragment).viewModel
        val article = args.article
        title.text = article.title
        date.text = article.publishedAt
        subtitle_on_appbar.text = article.url
        description.text = article.description
        Glide.with(this).load(article.urlToImage).into(backdrop)
        web_button.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("article",article)

            }

            findNavController().navigate(
                R.id.action_newsDetailFragment_to_webFragment,
                bundle
            )
        }





    }


}