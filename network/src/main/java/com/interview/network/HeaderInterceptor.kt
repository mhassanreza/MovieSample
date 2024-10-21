package com.interview.network

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(@ApplicationContext private val context: Context) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val language: String =
//            if (BaseApplication.getCurrentLanguage() == Constants.Keys.LANGUAGE_ENGLISH) {
//                "en-US"
//            } else {
//                "ar-AE"
//            }
//        language.let {
            requestBuilder.addHeader(
                "Language", "en-US"
            ).toString()
//        }
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}



