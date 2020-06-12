package com.iherman.util

import android.util.Log

/**
 * Created by Illia Herman on 12.06.2020.
 */
inline fun <reified T> T.logInfo(message: String) = Log.i(T::class.java.simpleName, message)
inline fun <reified T> T.logError(message: String) = Log.e(T::class.java.simpleName, message)
inline fun <reified T> T.logDebug(message: String) = Log.d(T::class.java.simpleName, message)
inline fun <reified T> T.logWarn(message: String) = Log.w(T::class.java.simpleName, message)
inline fun <reified T> T.logVerbose(message: String) = Log.v(T::class.java.simpleName, message)
inline fun <reified T> T.logWTF(message: String) = Log.wtf(T::class.java.simpleName, message)
