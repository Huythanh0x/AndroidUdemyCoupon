package com.example.androidudemycoupon.ui.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidudemycoupon.data.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository) :
    ViewModel() {
    val isDarkMode = MutableLiveData<Boolean>()
    val isCouponNotification = MutableLiveData<Boolean>()
    val isNotificationByCategories = MutableLiveData<Boolean>()
    val isNotificationByKeywords = MutableLiveData<Boolean>()
    val listPreferCategories = MutableLiveData<Set<String>>()
    val listPreferKeywords = MutableLiveData<Set<String>>()

    init {
        getDarkMode()
        getCouponNotification()
        getPreferCategories()
        getPreferKeywords()
        getNotificationByCategories()
        getNotificationByKeywords()
    }

    private fun getDarkMode() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.getDarkMode().collect {
                isDarkMode.postValue(it)
            }
        }
    }

    fun updateDarkMode(isDarkMode: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveDarkMode(isDarkMode)
        }
    }

    private fun getNotificationByKeywords() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.getNotificationByKeyword().collect {
                isNotificationByKeywords.postValue(it)
            }
        }
    }

    fun updateNotificationByKeywords(isNotificationByKeywords: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveNotificationByKeyword(isNotificationByKeywords)
        }
    }

    private fun getNotificationByCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.getNotificationByCategories().collect {
                isNotificationByCategories.postValue(it)
            }
        }
    }

    fun updateNotificationByCategories(isNotificationByCategories: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveNotificationByCategories(isNotificationByCategories)
        }
    }

    private fun getCouponNotification() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.getCouponNotification().collect {
                isCouponNotification.postValue(it)
            }
        }
    }

    fun updateCouponNotification(isCouponNotification: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveCouponNotification(isCouponNotification)
        }
    }

    private fun getPreferCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.getPreferCategories().collect() {
                listPreferCategories.postValue(it)
            }
        }
    }

    fun addPreferCategory(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.addPreferCategories(category)
        }
    }

    fun removePreferCategory(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.removePreferCategories(category)
        }
    }

    private fun getPreferKeywords() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.getPreferKeywords().collect() {
                listPreferKeywords.postValue(it)
            }
        }
    }

    fun addPreferKeyword(keyword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.addPreferKeywords(keyword)
        }
    }

    fun removePreferKeyword(keyword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.removePreferKeywords(keyword)
        }
    }
}