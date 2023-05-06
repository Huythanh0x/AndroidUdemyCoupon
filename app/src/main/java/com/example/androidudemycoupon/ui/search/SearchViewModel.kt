package com.example.androidudemycoupon.ui.search

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidudemycoupon.data.DataStoreRepository
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
class SearchViewModel @Inject constructor(
    private val repository: Repository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {
    val allCoupons = repository.localDataSource.getAllCoupons()
    val displayCoupons = MutableLiveData<List<Coupon>>()
    private val couponsResponse: MutableLiveData<NetWorkResult<UdemyCouponCourse>> =
        MutableLiveData()
    val fetchedDateTime = MutableLiveData<String>()

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

    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchAllCoupons() {
        getFetchedDateTime()
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.remoteDataSource.fetchAllCoupons()
            Log.d("RESPONSE RESULT", response.toString())
            extractDataResponse(response).also {
                it.data?.localTime?.let { newFetchDateTime ->
                    Log.d("NEW FETCHED DATE TIME", newFetchDateTime)
                    Log.d("OLD FETCHED DATE TIME", fetchedDateTime.value.toString())
                    if (fetchedDateTime.value == newFetchDateTime) return@launch
                }
                couponsResponse.postValue(it)
                it.data?.coupons?.let { coupons ->
                    clearAllCoupons()
                    insertCoupons(coupons)
                }
                //TODO store the fetched datetime
                it.data?.localTime?.let { fetchedTime ->
                    updateFetchedDateTime(fetchedTime)
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

    fun queryCoupons(querySearch: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val queryCoupons = repository.localDataSource.queryCoupons(querySearch)
            displayCoupons.postValue(queryCoupons)
        }
    }

    private fun updateFetchedDateTime(lastFetchDateTime: String) {
        viewModelScope.launch(Dispatchers.IO) {
            fetchedDateTime.postValue(lastFetchDateTime)
            dataStoreRepository.saveFetchedDateTime(lastFetchDateTime)
        }
    }

    private fun getFetchedDateTime() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.getFetchedDateTime().collect {
                fetchedDateTime.postValue(it)
            }
        }
    }
}