package com.srisathyasaividyavahini.lmslite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myWebView: WebView = findViewById(R.id.webview)
        myWebView.getSettings().setJavaScriptEnabled(true)
        myWebView.loadUrl("https://sssvidyavahini.org")
    }
}