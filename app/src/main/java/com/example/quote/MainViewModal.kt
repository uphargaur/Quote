package com.example.quote

import android.content.Context
import android.nfc.Tag
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.io.InputStream
import android.util.Log

class MainViewModal(val context: Context) :ViewModel() {

    var quoteList: Array <Quote> = emptyArray()
    var index =5
    init {
        quoteList =loadQuoteFromAssets()
    }

    private fun loadQuoteFromAssets(): Array<Quote> {
        val inputStream: InputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        Log.e("model","yeh model class tak chal rha")
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Quote>::class.java)
    }

    fun getQuote() = quoteList[index]

    fun nextQuote() = quoteList[++index % quoteList.size]

    fun previousQuote() = quoteList[(--index + quoteList.size) % quoteList.size]

}