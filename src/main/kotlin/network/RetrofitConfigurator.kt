package network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BaseUrl = "https://api.spoonacular.com/"

private val client = OkHttpClient.Builder().build()

val retrofit: RecipeApi = Retrofit.Builder()
    .baseUrl(BaseUrl)
    .client(client)
    .addConverterFactory(
        MoshiConverterFactory.create(
            Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()
        )
    )
    .build()
    .create(RecipeApi::class.java)