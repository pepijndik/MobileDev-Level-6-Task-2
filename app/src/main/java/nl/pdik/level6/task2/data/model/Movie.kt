package nl.pdik.level6.task2.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Movie(
    @SerializedName("title") val title: String,
    @SerializedName("vote_count")  val vote_count: Int,
    @SerializedName("poster_path")  val poster: String?,
    @SerializedName("backdrop_path")  val backdrop: String?,
    @SerializedName("release_date")  val releaseDate: Date,
    @SerializedName("overview")  val overview: String?
)
