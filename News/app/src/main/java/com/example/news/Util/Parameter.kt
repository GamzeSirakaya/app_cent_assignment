package com.example.news.Util

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


