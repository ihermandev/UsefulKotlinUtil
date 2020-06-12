package com.iherman.util

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.*

/**
 * Created by Illia Herman on 11.06.2020.
 */

/**
 * Email validator
 */
fun String.isEmail(): Boolean =
    android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

/**
 * String to OkHttp RequestBody
 */
fun String.toRequestBody() = toRequestBody("multipart/form-data".toMediaTypeOrNull())

/**
 * Capitalize first letter of a string
 */
fun String.capitalizeFirstLetter(): String =
    this.substring(0, 1).toUpperCase(Locale.ROOT) + this.substring(1).toLowerCase(
        Locale.ROOT
    )