package cucerdariancatalin.marvel.binding

import android.graphics.Color
import android.view.View
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.whatif.whatIfNotNull
import com.skydoves.whatif.whatIfNotNullAs
import com.skydoves.whatif.whatIfNotNullOrEmpty
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import cucerdariancatalin.marvel.extensions.circularRevealedAtCenter
import cucerdariancatalin.marvel.model.Poster
import cucerdariancatalin.marvel.model.PosterDetails
import cucerdariancatalin.marvel.view.adapter.PosterAdapter
import cucerdariancatalin.marvel.view.adapter.PosterSeriesAdapter

object RecyclerViewBinding {
    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(view: RecyclerView, baseAdapter: RecyclerView.Adapter<*>) {
        view.adapter = baseAdapter
    }

    @JvmStatic
    @BindingAdapter("toast")
    fun bindToast(view: RecyclerView, text: String?) {
        text.whatIfNotNullOrEmpty {
            Toast.makeText(view.context, it, Toast.LENGTH_SHORT).show()
        }
    }

    @JvmStatic
    @BindingAdapter("adapterPosterList")
    fun bindAdapterPosterList(view: DiscreteScrollView, posters: List<Poster>?) {
        posters.whatIfNotNullOrEmpty { items ->
            view.adapter.whatIfNotNullAs<PosterAdapter> { adapter ->
                adapter.updatePosterList(items)
            }
        }
        view.setItemTransformer(
            ScaleTransformer.Builder()
                .setMaxScale(1.25f)
                .setMinScale(0.8f)
                .build()
        )
    }

    @JvmStatic
    @BindingAdapter("bindOnItemChanged", "bindOnItemChangedBackground")
    fun bindOnItemChanged(view: DiscreteScrollView, adapter: PosterAdapter, pointView: View) {
        view.addOnItemChangedListener { viewHolder, _ ->
            viewHolder?.adapterPosition.whatIfNotNull {
                pointView.circularRevealedAtCenter(Color.parseColor(adapter.getPoster(it).color))
            }
        }
    }

    @JvmStatic
    @BindingAdapter("adapterPosterSeries", "adapterPosterDetailsList")
    fun bindAdapterPosterDetailsList(
        view: RecyclerView,
        adapter: PosterSeriesAdapter,
        posters: List<PosterDetails>?
    ) {
        posters.whatIfNotNullOrEmpty {
            view.adapter = adapter.apply { updatePosterDetailsList(it) }
        }
    }
}