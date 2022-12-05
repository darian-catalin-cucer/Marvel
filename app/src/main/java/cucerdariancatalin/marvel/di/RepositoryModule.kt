package cucerdariancatalin.marvel.di

import cucerdariancatalin.marvel.repository.DetailRepository
import cucerdariancatalin.marvel.repository.MainRepository
import org.koin.dsl.module

val repositoryModule = module {

    single { MainRepository(get(), get(), get()) }

    single { DetailRepository(get()) }
}