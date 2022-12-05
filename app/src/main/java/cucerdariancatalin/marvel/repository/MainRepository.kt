package cucerdariancatalin.marvel.repository

import androidx.annotation.WorkerThread
import com.skydoves.sandwich.*
import com.skydoves.sandwich.disposables.CompositeDisposable
import cucerdariancatalin.marvel.mapper.ErrorResponseMapper
import cucerdariancatalin.marvel.model.Poster
import cucerdariancatalin.marvel.network.MarvelClient
import cucerdariancatalin.marvel.persistence.PosterDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class MainRepository constructor(
    private val marvelClient: MarvelClient,
    private val marvelDataSource: ResponseDataSource<List<Poster>>,
    private val posterDao: PosterDao
) : Repository {

    init {
        Timber.d("Injection MainRepository")
    }

    @WorkerThread
    @ExperimentalCoroutinesApi
    fun loadMarvelPosters(
        disposable: CompositeDisposable,
        coroutineScope: CoroutineScope,
        error: (String?) -> Unit
    ) = callbackFlow {
        val posters = posterDao.getPosterList()
        if (posters.isEmpty()) {
            /**
             * fetch [Poster] from the network and getting [ApiResponse] asynchronously.
             * @see [onSuccess](https://github.com/skydoves/sandwich#onsuccess-onerror-onexception)
             * */
            /**
             * fetch [Poster] from the network and getting [ApiResponse] asynchronously.
             * @see [onSuccess](https://github.com/skydoves/sandwich#onsuccess-onerror-onexception)
             * */
            marvelClient.fetchMarvelPosters(marvelDataSource, disposable, coroutineScope) { apiResponse ->
                apiResponse
                    // handle the case when the API request gets a success response.
                    .suspendOnSuccess {
                        posterDao.insertPosterList(data)
                        send(data)
                    }
                    // handle the case when the API request gets an error response.
                    // e.g. internal server error.
                    .suspendOnError {
                        /** maps the [ApiResponse.Failure.Error] to the [PosterErrorResponse] using the mapper. */
                        /** maps the [ApiResponse.Failure.Error] to the [PosterErrorResponse] using the mapper. */
                        map(ErrorResponseMapper) { error("[Code: $code]: $message") }
                    }
                    // handle the case when the API request gets an exception response.
                    // e.g., network connection error.
                    .suspendOnException { error(message()) }
                close()
            }
        } else {
            send(posters)
            close()
        }
        awaitClose()
    }.flowOn(Dispatchers.IO)
}