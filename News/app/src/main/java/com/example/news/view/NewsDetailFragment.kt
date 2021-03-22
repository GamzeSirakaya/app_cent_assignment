package com.example.news.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.util.dateFormat
import com.example.news.util.dateToTimeFormat
import kotlinx.android.synthetic.main.fragment_news_detail.*
import kotlinx.android.synthetic.main.recycler_item.*



class NewsDetailFragment : Fragment(R.layout.fragment_news_detail) {

    val args: NewsDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = args.article
        detail_title.text = article.title
       detail_publishedAt.text = article.publishedAt.dateFormat()
        detail_source.text=article.source.name
        detail_desc.text = article.description
        detail_hour.text=article.publishedAt?.dateToTimeFormat()
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

        shareBtn.setOnClickListener {
            try {
                val i = Intent(Intent.ACTION_SEND)
                i.type = "text/plan"
                i.putExtra(Intent.EXTRA_SUBJECT, args.article.title)
                val body: String =
                    args.article.title.toString() + "\n" + args.article.url + "\n" + "Haber Paylaşımı" + "\n"
                i.putExtra(Intent.EXTRA_TEXT, body)
                startActivity(Intent.createChooser(i, "Share with :"))
            } catch (e: Exception) {

            }
        }




    }


}