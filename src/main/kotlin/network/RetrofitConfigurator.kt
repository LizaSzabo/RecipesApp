package network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BaseUrl = "https://api.spoonacular.com/"

private val client = OkHttpClient.Builder().build()

val retrofit = Retrofit.Builder()
    .baseUrl(BaseUrl)
    .client(client)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()
    .create(RecipeApi::class.java)