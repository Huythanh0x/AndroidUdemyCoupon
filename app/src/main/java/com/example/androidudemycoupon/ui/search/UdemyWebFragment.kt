package com.example.androidudemycoupon.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.androidudemycoupon.R

class UdemyWebFragment : Fragment() {
    val args by navArgs<UdemyWebFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_udemy_web, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val webView = view.findViewById<WebView>(R.id.webView)
        webView.settings.apply {
            // Enable JavaScript
            javaScriptEnabled = true
            // Enable support for cookies
            javaScriptCanOpenWindowsAutomatically = true
            domStorageEnabled = true
            databaseEnabled = true
//            setAppCacheEnabled(true)
            // Enable support for file uploads
            allowFileAccess = true
            allowFileAccessFromFileURLs = true
            allowUniversalAccessFromFileURLs = true
            setSupportZoom(true)
            builtInZoomControls = true
            displayZoomControls = false
            // Enable support for saving form data
            saveFormData = true
            // Enable support for autofill of user data
            savePassword = true
        }
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                // Load the URL in the WebView
                view?.loadUrl(request?.url.toString())
                return true
            }
        }
        webView.loadUrl(args.coupon.couponUrl)

        super.onViewCreated(view, savedInstanceState)
    }
}