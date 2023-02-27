package nl.pdik.level6.task2.data.api

import nl.pdik.level6.task2.data.model.Movie
import nl.pdik.level6.task2.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/3/search/movie")
    suspend fun getMovies(@Query("api_key") apiKey: String? = null, @Query("query") query: String): Response<MovieResponse>

    @GET("/3/movie/{movie_id}")
    suspend fun getMovies(
        @Query("api_key") apiKey: String? = null,
        @Path("movie_id") movieId: Int): Movie
}
