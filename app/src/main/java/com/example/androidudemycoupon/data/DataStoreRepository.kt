package com.example.androidudemycoupon.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
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
}