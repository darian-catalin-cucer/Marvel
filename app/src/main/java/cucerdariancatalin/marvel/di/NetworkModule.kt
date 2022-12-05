package cucerdariancatalin.marvel.di

import com.skydoves.sandwich.ResponseDataSource
import cucerdariancatalin.marvel.model.Poster
import cucerdariancatalin.marvel.network.MarvelClient
import cucerdariancatalin.marvel.network.MarvelService
import cucerdariancatalin.marvel.network.RequestInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl("https://gist.githubusercontent.com/skydoves/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(MarvelService::class.java) }

    single { MarvelClient(get()) }

    single { ResponseDataSource<List<Poster>>() }
}
