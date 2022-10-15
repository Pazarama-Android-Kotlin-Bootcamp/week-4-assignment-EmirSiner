package com.example.patikaweek4.data.api

import com.example.patikaweek4.BuildConfig
import com.example.patikaweek4.data.api.interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        private lateinit var apiService: ApiService

        fun getApiService() : ApiService {
            if (!::apiService.isInitialized) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getHttpClient())
                    .build()

                apiService = retrofit.create(ApiService::class.java)
            }
            return apiService
        }
        private fun getHttpClient() : OkHttpClient {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(AuthInterceptor())
            return httpClient.build()
        }
    }
}