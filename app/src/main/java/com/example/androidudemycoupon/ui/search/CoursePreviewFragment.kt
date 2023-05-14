package com.example.androidudemycoupon.ui.search

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import android.widget.VideoView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.androidudemycoupon.databinding.FragmentCoursePreviewBinding
import com.example.androidudemycoupon.ui.MainActivity


class CoursePreviewFragment : Fragment() {
    var _binding: FragmentCoursePreviewBinding? = null
    val binding get() = _binding!!
    private val args by navArgs<CoursePreviewFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        (requireActivity() as MainActivity).showBottomNavigation(false)
        _binding = FragmentCoursePreviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val previewUrl = "https://www.udemy.com" + args.coupon.previewVideo
        videoPlayView = VideoView(requireContext())

        binding.previewWebview.settings.apply {
            domStorageEnabled = true
            allowFileAccess = true
            allowContentAccess = true
            setSupportMultipleWindows(true)
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            pluginState = WebSettings.PluginState.ON
            mediaPlaybackRequiresUserGesture = false
        }

        binding.previewWebview.webChromeClient = object : WebChromeClient() {
            override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
                super.onShowCustomView(view, callback)
                if (view is FrameLayout) {
                    val activity = requireActivity()
                    activity.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                    view.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
                    binding.previewWebview.visibility = View.GONE
                    binding.videoContainer.addView(
                        view, FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                    )
                    videoPlayView = view
                    fullScreenCallback = callback
                }
            }

            override fun onHideCustomView() {
                super.onHideCustomView()
                val activity = requireActivity()
                activity.window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                videoPlayView?.visibility = View.GONE
                videoPlayView = null
                fullScreenCallback?.onCustomViewHidden()
                fullScreenCallback = null
                binding.previewWebview.visibility = View.VISIBLE
            }
        }


        binding.previewWebview.apply {
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    binding.progressBarContainer.isVisible = true
                    super.onPageStarted(view, url, favicon)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    binding.progressBarContainer.isVisible = false
                    binding.previewWebview.isVisible = true
                    super.onPageFinished(view, url)
                }
            }
//            webChromeClient = WebChromeClient()
            setLayerType(View.LAYER_TYPE_HARDWARE, null)
            loadUrl(previewUrl)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private var videoPlayView: View? = null
    private var fullScreenCallback: WebChromeClient.CustomViewCallback? = null

}
