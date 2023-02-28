package com.example.quote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony.Mms.Intents
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {
    lateinit var mainViewModal: MainViewModal
    val quotetext :TextView
    get() = findViewById(R.id.quoteText)
    val quoteAuthor :TextView
    get() = findViewById(R.id.quoteAuthor)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModal = ViewModelProvider(this, MainViewModelFactory(application)).get(MainViewModal::class.java)
    }
    fun setquote(quote: Quote)
    {
        quotetext.text=quote.text
        quoteAuthor.text=quote.author

    }
    fun onNext(view: View) {
        setquote(mainViewModal.nextQuote())
    }
    fun onPrevious(view: View) {
        setquote(mainViewModal.previousQuote())
    }
    fun onShare(view: View) {
      val  intent=Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT,mainViewModal.getQuote().text)
        startActivity(intent)
    }
}