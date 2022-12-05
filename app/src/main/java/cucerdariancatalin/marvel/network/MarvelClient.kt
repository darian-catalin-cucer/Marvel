package cucerdariancatalin.marvel.network

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.DataRetainPolicy
import com.skydoves.sandwich.ResponseDataSource
import com.skydoves.sandwich.disposables.CompositeDisposable
import cucerdariancatalin.marvel.model.Poster
import kotlinx.coroutines.CoroutineScope

class MarvelClient(private val marvelService: MarvelService) {

    fun fetchMarvelPosters(
        dataSource: ResponseDataSource<List<Poster>>,
        disposable: CompositeDisposable,
        coroutineScope: CoroutineScope,
        onResult: suspend (response: ApiResponse<List<Poster>>) -> Unit
    ) {
        dataSource
            // Retain fetched data on the memory storage temporarily.
            // If request again, returns the retained data instead of re-fetching from the network.
            .dataRetainPolicy(DataRetainPolicy.RETAIN)
            // retry fetching data 3 times with 5000 milli-seconds time interval when the request gets failure.
            .retry(3, 5000L)
            // joins onto CompositeDisposable as a disposable and dispose onCleared().
            .joinDisposable(disposable)
            // combine network service to the data source.
            .suspendCombine(marvelService.fetchMarvelPosterList(), coroutineScope, onResult)
            // request API network call asynchronously.
            // if the request is successful, the data source will hold the success data.
            // in the next request after success, returns the cached API response with data.
            .request()
    }
}