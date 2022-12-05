package cucerdariancatalin.marvel.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.bindables.binding
import cucerdariancatalin.marvel.R
import cucerdariancatalin.marvel.databinding.ItemPosterBinding
import cucerdariancatalin.marvel.model.Poster
import cucerdariancatalin.marvel.view.ui.details.PosterDetailActivity

class PosterAdapter : RecyclerView.Adapter<PosterAdapter.PosterViewHolder>() {

    private val items = mutableListOf<Poster>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterViewHolder {
        val binding = parent.binding<ItemPosterBinding>(R.layout.item_poster)
        return PosterViewHolder(binding).apply {
            binding.root.setOnClickListener { view ->
                val position =
                    adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return@setOnClickListener
                PosterDetailActivity.startActivity(
                    view.context,
                    binding.transformationLayout,
                    items[position]
                )
            }
        }
    }

    fun updatePosterList(posters: List<Poster>) {
        items.clear()
        items.addAll(posters)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PosterViewHolder, position: Int) {
        holder.binding.apply {
            poster = items[position]
            veil = itemVeilLayout
            executePendingBindings()
        }
    }

    fun getPoster(index: Int): Poster = items[index]

    class PosterViewHolder(val binding: ItemPosterBinding) :
        RecyclerView.ViewHolder(binding.root)
}