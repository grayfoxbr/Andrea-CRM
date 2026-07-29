package com.example.appauthbase.data.remote

import com.example.appauthbase.config.AuthInterceptor
import com.example.appauthbase.config.NetworkConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ResourceApiProvider {

private val okHttpClient: OkHttpClient by lazy {
OkHttpClient.Builder()
.addInterceptor(AuthInterceptor())
.build()
}

private val retrofit: Retrofit by lazy {
Retrofit.Builder()
.baseUrl(NetworkConfig.GATEWAY_BASE_URL + "/")
.client(okHttpClient)
.addConverterFactory(GsonConverterFactory.create())
.build()
}

val companyApi: CompanyApi by lazy {
retrofit.create(CompanyApi::class.java)
}
}