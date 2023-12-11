package com.higor.search.app.data.datasource.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

internal class RecentSearchDataSourceImpl(private val context: Context) : RecentSearchDataSource {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_search_store")

    override suspend fun insertSearches(searches: List<String>) {
        val json = Gson().toJson(searches)

        context.dataStore.edit { preferences ->
            preferences[SEARCH_LIST] = json
        }
    }

    override suspend fun getSearches(): List<String> {
        val userPreferencesFlow: Flow<List<String>> = context.dataStore.data
            .map { preferences ->
                val json = preferences[SEARCH_LIST]

                json?.let {
                    Gson().fromJson(json, object : TypeToken<ArrayList<String>>() {}.type)
                } ?: run {
                    listOf()
                }
            }

        return userPreferencesFlow.firstOrNull() ?: listOf()
    }

    private companion object {
        val SEARCH_LIST = stringPreferencesKey("SEARCH_LIST")
    }
}
