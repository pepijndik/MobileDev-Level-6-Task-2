package nl.pdik.level6.task2.data.api

import nl.pdik.level6.task2.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Api {
    companion object {
        private const val BASE_URL = "https://api.themoviedb.org"

        // the lazy keyword makes sure the createApi function is not called until these properties are accessed
        val Client by lazy { createApi(BASE_URL) }

        private fun createApi(baseUrl: String): ApiService {

            val apiKeyInterceptor = Interceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url
                val url = originalHttpUrl.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build()
                val requestBuilder = original.newBuilder().url(url)
                val request = requestBuilder.build()
                chain.proceed(request)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(apiKeyInterceptor)
                .addInterceptor(HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY))
//                .readTimeout(10, TimeUnit.SECONDS)
//                .writeTimeout(10, TimeUnit.SECONDS)
                .build()

            // Create the Retrofit instance
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}
