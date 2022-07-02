package com.vilocorp.appdemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vilocorp.appdemo.databinding.ActivityWebPageBinding

class WebPage : AppCompatActivity() {
    private val binding: ActivityWebPageBinding by lazy {
        ActivityWebPageBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val url = intent.extras!!.getString("url")!!
        val title = intent.extras!!.getString("title")!!

        binding.title.text = title
        binding.webView.loadUrl(url)
        binding.btnRegresar.setOnClickListener {
            val mainintent = Intent(applicationContext, MainActivity::class.java)
            startActivity(mainintent)
        }
    }
}