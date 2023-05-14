package com.example.androidudemycoupon.ui.search

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.androidudemycoupon.databinding.FragmentDetailCouponBinding
import com.example.androidudemycoupon.ui.MainActivity

class DetailCouponFragment : Fragment() {
    var _binding: FragmentDetailCouponBinding? = null
    val binding get() = _binding!!
    private val args by navArgs<DetailCouponFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        (requireActivity() as MainActivity).showBottomNavigation(false)
        _binding = FragmentDetailCouponBinding.inflate(inflater, container, false)
        binding.coupon = args.coupon
        binding.previewBtn.setOnClickListener {
            val action =
                DetailCouponFragmentDirections.actionDetailCouponFragmentToCoursePreviewFragment(
                    args.coupon
                )
            findNavController().navigate(action)
        }
        binding.shareBtn.setOnClickListener {
            shareLink(args.coupon.couponUrl, args.coupon.title, requireContext())
        }

        binding.openBrowserBtn.setOnClickListener {
            openLinkInBrowser(args.coupon.couponUrl, requireContext())
        }
        binding.ratingBar.setOnClickListener {
            Log.d("RATING BAR", "Clicked")
        }
        binding.enrollNowBtn.setOnClickListener {
            val action =
                DetailCouponFragmentDirections.actionDetailCouponFragmentToUdemyWebFragment(args.coupon)
            findNavController().navigate(action)
        }
        return binding.root
    }
}

fun shareLink(url: String, title: String, context: Context) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_TEXT, url)
    context.startActivity(Intent.createChooser(intent, title))
}

fun openLinkInBrowser(url: String, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    context.startActivity(intent)
}