package nl.pdik.level6.task2.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.pdik.level6.task2.data.api.util.Resource
import nl.pdik.level6.task2.data.model.Movie
import nl.pdik.level6.task2.data.repository.MovieRepository

class MoviesViewModel(application: Application) : AndroidViewModel(application) {
    private val movieRepository = MovieRepository()

    var selectedMovie: Movie? = null
        private set

    /**
     * Expose non MutableLiveData via getter
     * errorText can be observed from Activity for error showing
     * Encapsulation :)
     */
    val moviesResource: LiveData<Resource<List<Movie>>>
        get() = _moviesResource

    //initialize it with an Empty type of Resource
    private val _moviesResource: MutableLiveData<Resource<List<Movie>>> = MutableLiveData(Resource.Empty())


    val movieResource: LiveData<Resource<Movie>>
        get() = _movieResource

    //initialize it with an Empty type of Resource
    private val _movieResource: MutableLiveData<Resource<Movie>> = MutableLiveData(Resource.Empty())

    /**
     * The viewModelScope is bound to Dispatchers.Main and will automatically be cancelled when the ViewModel is cleared.
     * Extension method of lifecycle-viewmodel-ktx library
     */
    fun getMovies() {
        //set resource type to loading
        _movieResource.value = Resource.Loading()
        viewModelScope.launch {
            _moviesResource.value = movieRepository.getMovies()
        }
    }
    fun getMovie(id: Int) {
        //set resource type to loading
        _movieResource.value = Resource.Loading()
        viewModelScope.launch {
            _movieResource.value = movieRepository.getMovie(id);
        }
    }
}
