package com.example.patikaweek4.data.api.interceptor
import com.example.patikaweek4.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val apiKeyRequest = originalRequest
            .newBuilder()
            .header(HEADER_NAME,BuildConfig.API_KEY)
            .build()

        return chain.proceed(apiKeyRequest)
    }
    companion object {
        private const val HEADER_NAME="X-Api-Key"
    }
}

