package cucerdariancatalin.marvel

import android.app.Application
import cucerdariancatalin.marvel.di.networkModule
import cucerdariancatalin.marvel.di.persistenceModule
import cucerdariancatalin.marvel.di.repositoryModule
import cucerdariancatalin.marvel.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MarvelHeroesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MarvelHeroesApplication)
            modules(networkModule)
            modules(persistenceModule)
            modules(repositoryModule)
            modules(viewModelModule)
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
