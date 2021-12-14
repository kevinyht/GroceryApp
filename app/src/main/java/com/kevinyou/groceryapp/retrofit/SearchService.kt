package com.kevinyou.groceryapp.retrofit

import com.kevinyou.groceryapp.model.Product
import retrofit2.Call
import retrofit2.http.*

interface SearchService {
    @GET("/grocery.json")
    fun getRequest(): Call<List<Product>>
// https://kevinyou.blob.core.windows.net/grocerydata/grocery.json
    //https://kevinyht-bucket-source.s3.us-east-2.amazonaws.com/grocery.json

    @GET("/grocery.json/{id}")
    fun getParamRequest(@Path("productId") id: String): Call<List<Product>>

    @FormUrlEncoded
    @PUT("/grocery.json/{id}")
    fun putRequest(@Path("id") id: String,
                   @Field("content") content: String): Call<Product>

    @DELETE("/grocery.json/{id}")
    fun deleteRequest(@Path("id") id: String): Call<Product>
}