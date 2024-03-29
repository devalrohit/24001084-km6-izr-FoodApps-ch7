package com.catnip.appfood_rohit.utils

import java.text.NumberFormat
import java.util.Locale

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

fun Double?.doubleToCurrency(language: String, country: String): String? {
    return try {
        val localeID = Locale(language, country)
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        numberFormat.format(this).toString()
    } catch (e: Exception) {
        null
    }
}
fun Double?.toRupiahFormat() = this.doubleToCurrency("id", "ID")