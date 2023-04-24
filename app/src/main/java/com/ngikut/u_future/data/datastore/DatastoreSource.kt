package com.ngikut.u_future.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import javax.inject.Inject

val Context.datastore:DataStore<Preferences> by preferencesDataStore("_datastore_")

class DatastoreSource @Inject constructor(
    private val context: Context
){
    val datastore = context.datastore
}