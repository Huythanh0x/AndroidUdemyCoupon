package com.example.androidudemycoupon.ui.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.androidudemycoupon.R
import com.example.androidudemycoupon.ui.MainActivity

class CourseFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as MainActivity).showBottomNavigation(true)
        return inflater.inflate(R.layout.fragment_course, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val webView = view.findViewById<WebView>(R.id.courseWebView)
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
        webView.loadUrl("https://www.udemy.com/home/my-courses/learning/")


        super.onViewCreated(view, savedInstanceState)
    }
}