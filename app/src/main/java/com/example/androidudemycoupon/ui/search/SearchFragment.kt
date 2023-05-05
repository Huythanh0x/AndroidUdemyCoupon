package com.example.androidudemycoupon.ui.search

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        searchViewModel.fetchAllCoupons()
        searchViewModel.allCoupons.observe(viewLifecycleOwner) {
            couponAdapter.data = it
            updateNumberOfTheResult(it.size)
        }
        searchViewModel.displayCoupons.observe(viewLifecycleOwner) {
            couponAdapter.data = it
            updateNumberOfTheResult(it.size)
        }
        binding.searchView.setOnQueryTextListener(queryQueryTextListener)

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    val queryQueryTextListener = object : SearchView.OnQueryTextListener {
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
        if(size > 1){
            binding.numberOfCoupon.text = "There are $size results"
        }else{
            binding.numberOfCoupon.text = "There are $size result"
        }
    }
}