package com.srisathyasaividyavahini.lmslite

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.KeyEvent
import android.webkit.*
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var myWebView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myWebView = findViewById(R.id.webview)
        myWebView.webViewClient = WebViewClient()
        myWebView.getSettings().setJavaScriptEnabled(true)
        myWebView.getSettings().setDomStorageEnabled(true)
		myWebView.loadUrl("https://sssvidyavahini.org")
		myWebView.setDownloadListener({ url, userAgent, contentDisposition, mimeType, contentLength ->  
			val request = DownloadManager.Request(Uri.parse(url))
			request.setMimeType(mimeType)  
			request.addRequestHeader("cookie", CookieManager.getInstance().getCookie(url))
			request.addRequestHeader("User-Agent", userAgent)  
			request.setDescription("Downloading file...")  
			request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimeType))
			//request.allowScanningByMediaScanner()  // deprecated
			request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)  
			request.setDestinationInExternalFilesDir(this@MainActivity, Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(
                                url, contentDisposition, mimeType))  
			val dm = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
			dm.enqueue(request)  
			Toast.makeText(applicationContext, "Downloading File", Toast.LENGTH_LONG).show()
		})
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Check if the key event was the Back button and if there's history
        if (keyCode == KeyEvent.KEYCODE_BACK && myWebView.canGoBack()) {
            myWebView.goBack()
            return true
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event)
    } 
}
