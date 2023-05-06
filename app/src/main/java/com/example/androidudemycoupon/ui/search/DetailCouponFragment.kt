package com.example.androidudemycoupon.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.androidudemycoupon.databinding.FragmentDetailCouponBinding

class DetailCouponFragment : Fragment() {
    var _binding: FragmentDetailCouponBinding? = null
    val binding get() = _binding!!
    private val args by navArgs<DetailCouponFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailCouponBinding.inflate(inflater, container, false)
        binding.coupon = args.coupon
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}