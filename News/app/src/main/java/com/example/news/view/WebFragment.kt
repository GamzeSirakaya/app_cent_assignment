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
import kotlinx.android.synthetic.main.fragment_news_detail.*
import kotlinx.android.synthetic.main.fragment_news_detail.title
import kotlinx.android.synthetic.main.fragment_web.*
import kotlinx.android.synthetic.main.recycler_item.*
import kotlinx.android.synthetic.main.recycler_item.view.*


class WebFragment : Fragment(R.layout.fragment_web) {
    val args: NewsDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = args.article
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }


    }


}