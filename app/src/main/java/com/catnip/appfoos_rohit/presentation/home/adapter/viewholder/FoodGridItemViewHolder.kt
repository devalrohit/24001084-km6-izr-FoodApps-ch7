package com.catnip.appfoos_rohit.presentation.home.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.catnip.appfood_rohit.data.model.Product
import com.catnip.appfood_rohit.databinding.ItemMenuGridBinding
import com.catnip.egroceries.core.ViewHolderBinder

class FoodGridItemViewHolder(
    private val binding: ItemMenuGridBinding,
    private val listener: (Product) -> Unit,
) : ViewHolder(binding.root), ViewHolderBinder<Product> {
    override fun bind(item: Product) {
        item.let {
            binding.ivMenuImage.load(it.imgUrl) {
                crossfade(true)
            }
            binding.tvMenuNameList.text = it.name
            binding.tvMenuPrice.text = it.price.toString()

            itemView.setOnClickListener {
                listener(item)
            }
        }
    }
}
