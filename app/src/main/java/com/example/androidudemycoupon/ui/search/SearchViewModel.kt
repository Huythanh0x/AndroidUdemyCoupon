package com.example.androidudemycoupon.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidudemycoupon.data.DataStoreRepository
import com.example.androidudemycoupon.data.Repository
import com.example.androidudemycoupon.model.Coupon
import com.example.androidudemycoupon.model.UdemyCouponCourse
import com.example.androidudemycoupon.util.Constants
import com.example.androidudemycoupon.util.NetWorkResult
import com.example.androidudemycoupon.util.SearchFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository, private val dataStoreRepository: DataStoreRepository
) : ViewModel() {
    //    val allCoupons = repository.localDataSource.getAllCoupons()
    val displayCoupons = MutableLiveData<List<Coupon>>()
    private val couponsResponse: MutableLiveData<NetWorkResult<UdemyCouponCourse>> =
        MutableLiveData()
    val fetchedDateTime = MutableLiveData<String>()
    var firstTimeFetchData = true
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

    fun getFilteredCoupons(
        searchFilter: SearchFilter
    ) {
        val language = if (searchFilter.languageFilter) "English" else ""
        val level = searchFilter.levelFilter.toList()
        val lowerRating = Constants.HASHMAP_RATING[searchFilter.ratingFilter]!!.first.toDouble()
        val upperRating = Constants.HASHMAP_RATING[searchFilter.ratingFilter]!!.second.toDouble()
        val lowerContentLength =
            Constants.HASHMAP_CONTENT_LENGTH[searchFilter.contentLengthFilter]!!.first * 60
        val upperContentLength =
            Constants.HASHMAP_CONTENT_LENGTH[searchFilter.contentLengthFilter]!!.second * 60

        viewModelScope.launch(Dispatchers.IO) {
            Log.d("LANGUAGE FILTER TAG", language)
            Log.d("LEVEL FILTER TAG", level.joinToString(" "))
            Log.d("RATING FILTER TAG", "lowerRating: $lowerRating upperRating: $upperRating")
            Log.d(
                "CONTENT LENGTH FILTER TAG",
                "lowerContentLength: $lowerContentLength upperContentLength: $upperContentLength"
            )
            displayCoupons.postValue(
                repository.localDataSource.getFilteredCoupons(
                    language,
                    level,
                    lowerRating,
                    upperRating,
                    lowerContentLength,
                    upperContentLength
                )
            )
        }
    }

    fun fetchAllCoupons(forceFetch: Boolean = false) {
        if (!firstTimeFetchData && !forceFetch) return
        getFetchedDateTime()
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.remoteDataSource.fetchAllCoupons()
            Log.d("RESPONSE RESULT", response.toString())
            extractDataResponse(response).also {
                it.data?.localTime?.let { newFetchDateTime ->
                    Log.d("NEW FETCHED DATE TIME", newFetchDateTime)
                    Log.d("OLD FETCHED DATE TIME", fetchedDateTime.value.toString())
                    if (fetchedDateTime.value != newFetchDateTime || displayCoupons.value?.isEmpty() == true) {
                        couponsResponse.postValue(it)
                        clearAllCoupons()
                        insertCoupons(it.data.coupons)
                        displayCoupons.postValue(it.data.coupons)
                        Log.d("UPDATE NEW COURSES TO DATABASE", it.data.coupons.size.toString())
                        //TODO store the fetched datetime
                        it.data.localTime.let { fetchedTime ->
                            updateFetchedDateTime(fetchedTime)
                        }
                    } else {
                        displayCoupons.postValue(repository.localDataSource.getAllCoupons())
                    }
                }
            }
        }
        firstTimeFetchData = false
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

    fun updateDisplayCouponData(newListCoupons: List<Coupon>) {
        displayCoupons.postValue(newListCoupons)
    }
}