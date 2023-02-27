package nl.pdik.level6.task2.data.repository

import android.util.Log
import kotlinx.coroutines.withTimeout
import nl.pdik.level6.task2.data.api.Api
import nl.pdik.level6.task2.data.api.ApiService
import nl.pdik.level6.task2.data.api.util.Resource
import nl.pdik.level6.task2.data.model.Movie

class MovieRepository {
    private val apiService: ApiService = Api.Client;
    /**
     * suspend function that calls a suspend function from the apiService call
     * @return result wrapped in our own Resource sealed class
     */
    suspend fun getMovies(): Resource<List<Movie>> {

        val response = try {
            withTimeout(5_000) {
                apiService.getMovies()
            }
        } catch (e: Exception) {
            Log.e("MovieRepository", e.message ?: "No exception message available")
            return Resource.Error("An unknown error occured")
        }

        return Resource.Success(response)
    }

    suspend fun getMovie(id:Int): Resource<Movie> {

        val response = try {
            withTimeout(5_000) {
                apiService.getMovies(movieId=id)
            }
        } catch (e: Exception) {
            Log.e("MovieRepository", e.message ?: "No exception message available")
            return Resource.Error("An unknown error occured")
        }

        return Resource.Success(response)
    }
}

