package com.example.androidudemycoupon.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidudemycoupon.data.Repository
import com.example.androidudemycoupon.model.Coupon
import com.example.androidudemycoupon.model.UdemyCouponCourse
import com.example.androidudemycoupon.util.NetWorkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    // TODO: Implement the ViewModel
    val allCoupons = repository.localDataSource.getAllCoupons()
    private val couponsResponse: MutableLiveData<NetWorkResult<UdemyCouponCourse>> =
        MutableLiveData()

    private fun insertCoupons(coupons: List<Coupon>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.localDataSource.insertCoupons(coupons)
        }
    }

    private fun clearAllCoupons() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.localDataSource.clearAllCoupons()
        }
    }

    fun fetchAllCoupons() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.remoteDataSource.fetchAllCoupons()
            Log.e("RESPONSE RESULT", response.toString())
            extractDataResponse(response).also {
                couponsResponse.postValue(it)
                it.data?.coupons?.let { coupons ->
                    clearAllCoupons()
                    insertCoupons(coupons)
                }

            }
        }
    }

    private fun extractDataResponse(response: Response<UdemyCouponCourse>): NetWorkResult<UdemyCouponCourse> {
        return when {
            response.message().toString().contains("timeout") -> {
                NetWorkResult.Error("Timeout")
            }

            response.isSuccessful -> {
                NetWorkResult.Success(response.body()!!)
            }

            else -> NetWorkResult.Error(response.message())
        }
    }
}