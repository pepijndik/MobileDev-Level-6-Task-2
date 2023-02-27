package nl.pdik.level6.task2.data.api

import nl.pdik.level6.task2.data.model.Movie
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/search/movie")
    suspend fun getMovies(): List<Movie>

    @GET("/movie/")
    suspend fun getMovies(@Path("movie_id") movieId: Int): Movie
}
