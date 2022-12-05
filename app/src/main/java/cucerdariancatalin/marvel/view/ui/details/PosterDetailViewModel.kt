package cucerdariancatalin.marvel.view.ui.details

import androidx.annotation.VisibleForTesting
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import cucerdariancatalin.marvel.model.Poster
import cucerdariancatalin.marvel.repository.DetailRepository
import kotlinx.coroutines.flow.Flow

class PosterDetailViewModel(
    posterId: Long,
    repository: DetailRepository
) : BindingViewModel() {

    @VisibleForTesting
    internal val posterFlow: Flow<Poster> = repository.getPosterById(posterId)

    @get:Bindable
    val poster: Poster? by posterFlow.asBindingProperty(viewModelScope, null)
}