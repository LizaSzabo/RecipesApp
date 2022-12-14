package network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchRecipeResult(
    val results: List<Result>
)

@JsonClass(generateAdapter = true)
data class Result(
    val id: String,
    val title: String,
    val image: String,
    val imageType: String,
)