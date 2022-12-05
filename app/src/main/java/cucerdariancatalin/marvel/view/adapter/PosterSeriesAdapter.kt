package cucerdariancatalin.marvel.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.bindables.binding
import cucerdariancatalin.marvel.R
import cucerdariancatalin.marvel.databinding.ItemPosterSeriesBinding
import cucerdariancatalin.marvel.databinding.LayoutPlotBinding
import cucerdariancatalin.marvel.model.PosterDetails

class PosterSeriesAdapter(
    private val layoutPlotBinding: LayoutPlotBinding
) : RecyclerView.Adapter<PosterSeriesAdapter.PosterSeriesViewHolder>() {

    private val items = mutableListOf<PosterDetails>()
    private var selectable = true

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PosterSeriesViewHolder {
        val binding = parent.binding<ItemPosterSeriesBinding>(R.layout.item_poster_series)
        return PosterSeriesViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position =
                    adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return@setOnClickListener
                if (selectable) {
                    selectable = false
                    layoutPlotBinding.details = items[position]
                    layoutPlotBinding.root.setOnClickListener {
                        binding.transformationLayout.finishTransform()
                        selectable = true
                    }
                    layoutPlotBinding.executePendingBindings()
                    binding.transformationLayout.bindTargetView(layoutPlotBinding.root)
                    binding.transformationLayout.startTransform()
                }
            }
        }
    }

    fun updatePosterDetailsList(posterDetails: List<PosterDetails>) {
        items.clear()
        items.addAll(posterDetails)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(
        holder: PosterSeriesViewHolder,
        position: Int
    ) {
        holder.binding.apply {
            details = items[position]
            executePendingBindings()
        }
    }

    class PosterSeriesViewHolder(val binding: ItemPosterSeriesBinding) :
        RecyclerView.ViewHolder(binding.root)
}