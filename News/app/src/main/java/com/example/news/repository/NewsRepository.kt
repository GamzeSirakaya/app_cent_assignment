package com.example.news.repository

import com.example.news.db.ArticleDatabase
import com.example.news.model.Article
import com.example.news.network.News


class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
    News.getService().getNews(countryCode,pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().insertAll(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle()
}