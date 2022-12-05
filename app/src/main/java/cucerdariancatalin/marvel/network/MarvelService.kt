package cucerdariancatalin.marvel.network

import cucerdariancatalin.marvel.model.Poster
import retrofit2.Call
import retrofit2.http.GET

interface MarvelService {

    @GET(
        "ae1f687d7e67865a3d217ff719e256f8/raw/592160d562604476acd2d4adfd9d383058c7c558/MarvelLists.json"
    )
    fun fetchMarvelPosterList(): Call<List<Poster>>
}