package com.android_demo.ui.detailscreen

import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.webkit.WebSettings
import com.android_demo.R
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.nav_header_main.view.*

/**
 * WebView Activity to show full article*/
class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initWebview()
    }



    private fun initWebview() {
        val url = intent?.extras?.getString("url", "")
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true

        webView.loadUrl(url) //load url to webview

    }

}
