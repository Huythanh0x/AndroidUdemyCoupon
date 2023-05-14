package com.example.androidudemycoupon.ui.search

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.androidudemycoupon.databinding.FragmentUdemyWebBinding

class UdemyWebFragment : Fragment() {
    val args by navArgs<UdemyWebFragmentArgs>()
    var _binding: FragmentUdemyWebBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUdemyWebBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.webView.settings.apply {
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
        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                binding.progressBarContainer.isVisible = true
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                binding.progressBarContainer.isVisible = false
                binding.webView.isVisible = true
                super.onPageFinished(view, url)
            }
        }
        binding.webView.loadUrl(args.coupon.couponUrl)

        super.onViewCreated(view, savedInstanceState)
    }
}