package com.example.androidudemycoupon.ui.course

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.androidudemycoupon.databinding.FragmentCourseBinding
import com.example.androidudemycoupon.ui.MainActivity

class CourseFragment : Fragment() {
    var _binding: FragmentCourseBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as MainActivity).showBottomNavigation(true)
        _binding = FragmentCourseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.courseWebView.settings.apply {
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
        binding.courseWebView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                binding.progressBarContainer.isVisible = true
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                binding.progressBarContainer.isVisible = false
                binding.courseWebView.isVisible = true
                super.onPageFinished(view, url)
            }
        }
        binding.courseWebView.loadUrl("https://www.udemy.com/home/my-courses/learning/")


        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}