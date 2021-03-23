package com.example.news.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.news.model.Article

@Dao
interface ArticleDao {

    @Insert
    suspend fun insertAll(vararg article: Article): List<Long>

    @Query("SELECT * FROM article")
    fun getAllArticles(): List<Article>

    @Query("DELETE FROM article")
    suspend fun deleteArticle()
}