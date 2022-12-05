package cucerdariancatalin.marvel.di

import cucerdariancatalin.marvel.view.ui.details.PosterDetailViewModel
import cucerdariancatalin.marvel.view.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MainViewModel(get()) }

    viewModel { (posterId: Long) -> PosterDetailViewModel(posterId, get()) }
}