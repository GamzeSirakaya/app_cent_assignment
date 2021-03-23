package com.example.news.util

import android.annotation.SuppressLint
import com.example.news.model.Article

import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.dateToTimeFormat(): String? {
    val p = PrettyTime(Locale(getCountry()))
    var isTime: String? = null
    try {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
        val date: Date = sdf.parse(this)
        isTime = p.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return isTime
}

@SuppressLint("SimpleDateFormat")
fun String?.dateFormat(): String? {
    if (this == "" || this == null)
        return ""
    val newDate: String?
    val dateFormat = SimpleDateFormat("E, d MMM yyyy", Locale(getCountry()))
    newDate = try {
        val date: Date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(this)
        dateFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
        this
    }
    return newDate
}

fun getCountry(): String? =
    Locale.getDefault().country.toLowerCase(Locale.ROOT)


fun getLanguage(): String? = Locale.getDefault().language


fun String.searchQuery(responseList: List<Article>?): MutableList<Article> {
    val responses: MutableList<Article> = ArrayList()
    if (responseList == null || responseList.isEmpty())
        return responses
    for (response in responseList) {
        val name: String? = response.title?.toLowerCase(Locale.ROOT)
        if (this.toLowerCase(Locale.ROOT)?.let { name?.contains(it) }!!)
            responses.add(response)
    }

    return responses
}