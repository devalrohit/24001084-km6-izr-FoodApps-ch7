package com.catnip.appfood_rohit.data.source.network.services

import com.catnip.appfood_rohit.BuildConfig
import com.catnip.appfoos_rohit.data.source.network.model.category.CategoriesResponse
import com.catnip.appfoos_rohit.data.source.network.model.products.ProductResponse
import com.example.foodiesapp.data.source.network.model.checkout.CheckoutRequestPayload
import com.example.foodiesapp.data.source.network.model.checkout.CheckoutResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface AppFoodRohitApiService {
    @GET("category")
    suspend fun getCategories(): CategoriesResponse
    @GET("listmenu")
    suspend fun getProduct(@Query("c") category : String? = null) : ProductResponse

    @POST("order")
    suspend fun createOrder(@Body payload : CheckoutRequestPayload): CheckoutResponse

    companion object {
        @JvmStatic
        operator fun invoke() :AppFoodRohitApiService {
            val okHttpClient= OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(AppFoodRohitApiService::class.java)
        }
    }
}