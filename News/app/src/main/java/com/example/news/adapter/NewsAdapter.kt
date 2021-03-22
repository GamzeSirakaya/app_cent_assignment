package com.example.news.adapter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.model.Article
import com.example.news.util.dateFormat
import kotlinx.android.synthetic.main.recycler_item.view.*



class NewsAdapter :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(img)
           source.text = article.source.name
            title.text = article.title
            desc.text = article.description
            publishedAt.text = article.publishedAt.dateFormat()
            setOnClickListener {
                onItemClickListener?.let { it(article)

                }
            }
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener

    }


    val differ = AsyncListDiffer(this, diffUtilCallBack)

    companion object {
        private val diffUtilCallBack =
            object : DiffUtil.ItemCallback<Article>() {
                override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                    return oldItem.title == newItem.title
                }

                override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                    return oldItem == newItem
                }

            }

    }

}



