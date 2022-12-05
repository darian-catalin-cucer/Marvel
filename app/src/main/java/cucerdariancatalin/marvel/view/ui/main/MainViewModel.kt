package cucerdariancatalin.marvel.view.ui.main

import androidx.annotation.VisibleForTesting
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import cucerdariancatalin.marvel.base.DisposableViewModel
import cucerdariancatalin.marvel.model.Poster
import cucerdariancatalin.marvel.repository.MainRepository
import timber.log.Timber

class MainViewModel constructor(
    mainRepository: MainRepository
) : DisposableViewModel() {

    @get:Bindable
    var toast: String? by bindingProperty(null)
        private set

    @VisibleForTesting
    internal val posterListFlow =
        mainRepository.loadMarvelPosters(disposables, viewModelScope) { toast = it }

    @get:Bindable
    val posterList: List<Poster>? by posterListFlow.asBindingProperty(viewModelScope, null)

    init {
        Timber.d("injection MainViewModel")
    }
}
