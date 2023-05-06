package com.example.androidudemycoupon.ui.search

import android.os.Bundle
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
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}