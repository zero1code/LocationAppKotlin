/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.locationkotlin.utils

import android.content.Context
import android.location.Location
import androidx.core.content.edit
import com.example.locationkotlin.R

/**
 * Retorna A `location` objeto como um texto legível para humanos.
 */
fun Location?.toText(): String {
    return if (this != null) {
        "($latitude, $longitude)"
    } else {
        "Unknown location"
    }
}

/**
 * Fornece acesso ao SharedPreferences para localização nas Activities e Services.
 */
internal object SharedPreferenceUtil {

    const val KEY_FOREGROUND_ENABLED = "tracking_foreground_location"
    const val KEY_CURRENT_LOCATION = "current_location"
    const val KEY_CURRENT_LATITUDE = "current_latitude"
    const val KEY_CURRENT_LONGITUDE = "current_longitude"

    /**
     * Retorna true se a requisição de atualização de localização foi atualizada, caso contrário retorna false.
     *
     * @param context The [Context].
     */
    fun getLocationTrackingPref(context: Context): Boolean =
        context.getSharedPreferences(
            context.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
            .getBoolean(KEY_FOREGROUND_ENABLED, false)

    /**
     * Salva o estado de atualizações da localização no SharedPreferences.
     * @param requestingLocationUpdates The location updates state.
     */
    fun saveLocationTrackingPref(context: Context, requestingLocationUpdates: Boolean) =
        context.getSharedPreferences(
            context.getString(R.string.preference_file_key),
            Context.MODE_PRIVATE).edit {
            putBoolean(KEY_FOREGROUND_ENABLED, requestingLocationUpdates)
        }

    fun saveCurrentLocationPref(context: Context, location: String, latitude: String, longitude: String) =
        context.getSharedPreferences(
            context.getString(R.string.preference_file_key),
            Context.MODE_PRIVATE).edit {
            putString(KEY_CURRENT_LOCATION, location)
            putString(KEY_CURRENT_LATITUDE, latitude)
            putString(KEY_CURRENT_LONGITUDE, longitude)
        }

    fun getCurrentLocationPref(context: Context): String? =
        context.getSharedPreferences(
            context.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
            .getString(KEY_CURRENT_LOCATION, context.getString(R.string.no_location_text))

    fun getCurrentLatitudePref(context: Context): String? =
        context.getSharedPreferences(
            context.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
            .getString(KEY_CURRENT_LATITUDE, context.getString(R.string.no_location_text))

    fun getCurrentLongitudePref(context: Context): String? =
        context.getSharedPreferences(
            context.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
            .getString(KEY_CURRENT_LONGITUDE, context.getString(R.string.no_location_text))
}
