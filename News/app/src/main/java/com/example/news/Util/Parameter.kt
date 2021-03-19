package com.example.news.util

import java.util.*

class  Parameter {

    companion object{
        fun   getCountry(): String {
            val local=Locale.getDefault()
            val country= local.country
            return country.toString().toLowerCase()

        }
    }
}


