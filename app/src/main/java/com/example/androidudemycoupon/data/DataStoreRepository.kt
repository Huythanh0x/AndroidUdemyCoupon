package com.example.androidudemycoupon.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.example.androidudemycoupon.util.Constants
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ActivityRetainedScoped
class DataStoreRepository @Inject constructor(private val dataStore: DataStore<Preferences>) {
    private object PreferencesKey {
        val fetchedDateTime = stringPreferencesKey(Constants.PREFERENCES_FETCHED_DATE_TIME)
        val darkMode = booleanPreferencesKey(Constants.PREFERENCES_DARK_MODE)
        val couponNotification = booleanPreferencesKey(Constants.PREFERENCES_COUPON_NOTIFICATION)
        val preferCategories = stringSetPreferencesKey(Constants.PREFERENCES_PREFER_CATEGORIES)
        val preferKeyWords = stringSetPreferencesKey(Constants.PREFERENCES_PREFER_KEYWORDS)
        val notificationByKeywords =
            booleanPreferencesKey(Constants.PREFERENCES_NOTIFICATION_BY_KEYWORDS)
        val notificationByCategories =
            booleanPreferencesKey(Constants.PREFERENCES_NOTIFICATION_BY_CATEGORIES)
    }

    suspend fun saveFetchedDateTime(fetchedDateTime: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.fetchedDateTime] = fetchedDateTime
        }
    }

    fun getFetchedDateTime(): Flow<String> {
        return dataStore.data.catch {
            throw Exception("ERROR AT getFetchedDateTime()")
        }.map {
            it[PreferencesKey.fetchedDateTime] ?: ""
        }
    }

    fun getDarkMode(): Flow<Boolean> {
        return dataStore.data.catch {
            throw java.lang.Exception("Error at getDarkMode()")
        }.map {
            it[PreferencesKey.darkMode] ?: false
        }
    }

    suspend fun saveDarkMode(isDarkMode: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.darkMode] = isDarkMode
        }
    }

    fun getNotificationByKeyword(): Flow<Boolean> {
        return dataStore.data.catch {
            throw java.lang.Exception("Error at getDarkMode()")
        }.map {
            it[PreferencesKey.notificationByKeywords] ?: true
        }
    }

    suspend fun saveNotificationByKeyword(isNotificationByKeywords: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.notificationByKeywords] = isNotificationByKeywords
        }
    }

    fun getNotificationByCategories(): Flow<Boolean> {
        return dataStore.data.catch {
            throw java.lang.Exception("Error at getDarkMode()")
        }.map {
            it[PreferencesKey.notificationByCategories] ?: true
        }
    }

    suspend fun saveNotificationByCategories(isNotificationByCategories: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.notificationByCategories] = isNotificationByCategories
        }
    }

    fun getCouponNotification(): Flow<Boolean> {
        return dataStore.data.catch {
            throw java.lang.Exception("Error at getDarkMode()")
        }.map {
            it[PreferencesKey.couponNotification] ?: true
        }
    }

    suspend fun saveCouponNotification(isCouponNotification: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.couponNotification] = isCouponNotification
        }
    }

    fun getPreferCategories(): Flow<Set<String>> {
        return dataStore.data.catch {
            throw java.lang.Exception("Error at getPreferCategories()")
        }.map { preferences ->
            preferences[PreferencesKey.preferCategories] ?: Constants.DEFAULT_PREFER_CATEGORIES
        }
    }

    suspend fun addPreferCategories(category: String) {
        dataStore.edit { preferences ->
            val currentSet =
                preferences[PreferencesKey.preferCategories]?.toMutableSet()
                    ?: Constants.DEFAULT_PREFER_CATEGORIES.toMutableSet()
            currentSet.add(category)
            Log.d("addPreferCategories", currentSet.toString())
            preferences[PreferencesKey.preferCategories] = currentSet
        }
    }

    suspend fun removePreferCategories(category: String) {
        dataStore.edit { preferences ->
            val currentSet =
                preferences[PreferencesKey.preferCategories]?.toMutableSet()
                    ?: Constants.DEFAULT_PREFER_CATEGORIES.toMutableSet()
            currentSet.remove(category)
            Log.d("addPreferCategories", currentSet.toString())
            preferences[PreferencesKey.preferCategories] = currentSet
        }
    }

    fun getPreferKeywords(): Flow<Set<String>> {
        return dataStore.data.catch {
            throw java.lang.Exception("Error at getPreferKeywords()")
        }.map { preferences ->
            preferences[PreferencesKey.preferKeyWords] ?: Constants.DEFAULT_PREFER_KEYWORDS
        }
    }

    suspend fun addPreferKeywords(keyword: String) {
        dataStore.edit { preferences ->
            val currentSet =
                preferences[PreferencesKey.preferKeyWords]?.toMutableSet()
                    ?: Constants.DEFAULT_PREFER_KEYWORDS.toMutableSet()
            currentSet.add(keyword)
            preferences[PreferencesKey.preferKeyWords] = currentSet
        }
    }

    suspend fun removePreferKeywords(keyword: String) {
        dataStore.edit { preferences ->
            val currentSet =
                preferences[PreferencesKey.preferKeyWords]?.toMutableSet()
                    ?: Constants.DEFAULT_PREFER_KEYWORDS.toMutableSet()
            currentSet.remove(keyword)
            Log.d("addPreferCategories", currentSet.toString())
            preferences[PreferencesKey.preferKeyWords] = currentSet
        }
    }
}