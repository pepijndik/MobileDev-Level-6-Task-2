package nl.pdik.level6.task2.data.api

import nl.pdik.level6.task2.data.model.Movie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/search/movie")
    suspend fun getMovies(@Query("api_key") apiKey: String? = null): List<Movie>

    @GET("/movie/")
    suspend fun getMovies(
        @Query("api_key") apiKey: String? = null,
        @Path("movie_id") movieId: Int): Movie
}
