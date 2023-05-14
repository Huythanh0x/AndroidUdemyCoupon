package com.example.androidudemycoupon.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidudemycoupon.databinding.FragmentSearchBinding
import com.example.androidudemycoupon.ui.MainActivity
import com.example.androidudemycoupon.ui.adapter.CouponRecyclerAdapter
import com.example.androidudemycoupon.util.TimeLeft
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    var _binding: FragmentSearchBinding? = null
    val binding get() = _binding!!
    val searchViewModel: SearchViewModel by viewModels()
    private lateinit var couponAdapter: CouponRecyclerAdapter
    private lateinit var sideSheet: SearchFilterSideSheetDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        (requireActivity() as MainActivity).showBottomNavigation(true)
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.shimmerRecyclerView.showShimmer()
        sideSheet = SearchFilterSideSheetDialog(requireContext())
        sideSheet.initState()
        sideSheet.onChipCheckListener() {
            searchViewModel.getFilteredCoupons(it)
        }
        couponAdapter = CouponRecyclerAdapter() {
            val action =
                SearchFragmentDirections.actionSearchFragmentToDetailCouponFragment(coupon = it)
            findNavController().navigate(action)
        }
        binding.shimmerRecyclerView.adapter = couponAdapter
//        searchViewModel.allCoupons.observe(viewLifecycleOwner) {
//            searchViewModel.updateDisplayCouponData(it)
//        }
        searchViewModel.displayCoupons.observe(viewLifecycleOwner) {
            binding.shimmerRecyclerView.hideShimmer()
            couponAdapter.data = it
            updateNumberOfTheResult(it.size)
        }
        searchViewModel.fetchedDateTime.observe(viewLifecycleOwner) {
            updateLastFetchDateTime(it)
        }
        binding.searchView.setOnQueryTextListener(queryQueryTextListener)
        binding.filterBtn.setOnClickListener {
            sideSheet.show()
            sideSheet.loadDefaultFilter() {
                searchViewModel.getFilteredCoupons(it)
            }
        }
        searchViewModel.fetchAllCoupons()
        binding.refreshLayout.apply {
            this.setOnRefreshListener {
                binding.shimmerRecyclerView.showShimmer()
                searchViewModel.fetchAllCoupons(forceFetch = true)
                binding.shimmerRecyclerView.hideShimmer()
                binding.refreshLayout.isRefreshing = false
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private val queryQueryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            searchViewModel.queryCoupons(query.toString())
            Log.d("SEARCH QUERY HERE", query.toString())
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
//            TODO
            return true
        }
    }

    private fun updateNumberOfTheResult(size: Int) {
        if (size > 1) {
            binding.numberOfCoupon.text = "There are $size results"
        } else {
            binding.numberOfCoupon.text = "There are $size result"
        }
    }

    private fun updateLastFetchDateTime(fetchedTime: String) {
        binding.lastTimeUpdate.text = TimeLeft.timeFromLastUpdate(fetchedTime)
    }
}