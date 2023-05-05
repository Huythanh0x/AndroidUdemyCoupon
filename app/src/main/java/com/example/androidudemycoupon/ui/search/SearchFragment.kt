package com.example.androidudemycoupon.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidudemycoupon.databinding.FragmentSearchBinding
import com.example.androidudemycoupon.ui.adapter.CouponRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    var _binding: FragmentSearchBinding? = null
    val binding get() = _binding!!
    private lateinit var viewModel: SearchViewModel
    val searchViewModel: SearchViewModel by viewModels()
    val couponAdapter = CouponRecyclerAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.shimmerRecyclerView.adapter = couponAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        searchViewModel.fetchAllCoupons()
        searchViewModel.allCoupons.observe(viewLifecycleOwner) {
            couponAdapter.data = it
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}