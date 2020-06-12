package com.iherman.util

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * Created by Illia Herman on 12.06.2020.
 */

/**
 * Unsafe OkHttp Client
 */
fun trustAllSSLCertificates(): OkHttpClient {
    val naiveTrustManager = object : X509TrustManager {
        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) = Unit
        override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) = Unit
    }

    val insecureSocketFactory = SSLContext.getInstance("SSL").apply {
        val trustAllCerts = arrayOf<TrustManager>(naiveTrustManager)
        init(null, trustAllCerts, SecureRandom())
    }.socketFactory

    val builder = OkHttpClient.Builder()
    builder.apply {
        sslSocketFactory(insecureSocketFactory, naiveTrustManager)
        hostnameVerifier(HostnameVerifier { _, _ -> true })
    }
    return builder.build()
}

fun makeDefaultHeaderInterceptor() = Interceptor { chain ->
    chain.proceed(
        chain.request().newBuilder()
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .build()
    )
}

fun makeLoggingInterceptor(isDebug: Boolean) = HttpLoggingInterceptor().apply {
    this.level =
        if (isDebug) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
}