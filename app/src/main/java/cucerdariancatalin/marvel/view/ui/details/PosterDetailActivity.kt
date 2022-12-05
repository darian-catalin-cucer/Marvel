package cucerdariancatalin.marvel.view.ui.details

import android.content.Context
import android.os.Bundle
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.bundle
import com.skydoves.bundler.intentOf
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout
import cucerdariancatalin.marvel.R
import cucerdariancatalin.marvel.databinding.ActivityPosterDetailBinding
import cucerdariancatalin.marvel.extensions.onTransformationEndContainerApplyParams
import cucerdariancatalin.marvel.model.Poster
import cucerdariancatalin.marvel.view.adapter.PosterSeriesAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PosterDetailActivity :
    BindingActivity<ActivityPosterDetailBinding>(R.layout.activity_poster_detail) {

    private val posterId: Long by bundle(EXTRA_POSTER_ID, 0)
    private val viewModel: PosterDetailViewModel by viewModel { parametersOf(posterId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationEndContainerApplyParams()
        super.onCreate(savedInstanceState)
        binding {
            dispatcher = this@PosterDetailActivity
            adapter = PosterSeriesAdapter(plot)
            vm = viewModel
        }
    }

    companion object {
        private const val EXTRA_POSTER_ID = "EXTRA_POSTER_ID"

        fun startActivity(
            context: Context,
            transformationLayout: TransformationLayout,
            poster: Poster
        ) = context.intentOf<PosterDetailActivity> {
            putExtra(EXTRA_POSTER_ID to poster.id)
            TransformationCompat.startActivity(transformationLayout, intent)
        }
    }
}