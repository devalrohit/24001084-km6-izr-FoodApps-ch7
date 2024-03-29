package com.catnip.appfood_rohit.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.catnip.egroceries.core.ViewHolderBinder
import com.catnip.appfood_rohit.data.model.Product
import com.catnip.appfood_rohit.databinding.ItemMenuGridBinding
import com.catnip.appfood_rohit.databinding.ItemMenuListBinding
import com.example.appfood_rohit.presentation.home.adapter.FoodGridItemViewHolder
import com.example.appfood_rohit.presentation.home.adapter.FoodListItemViewHolder


class ProductListAdapter(
    private val listener: OnItemClickedListener<Product>,
    private val listMode: Int = MODE_GRID
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val MODE_LIST = 0
        const val MODE_GRID = 1
    }

    private var asyncDataDiffer = AsyncListDiffer(
        this, object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                //membandingkan apakah item tersebut sama
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                // yang dibandingkan adalah kontennya
                return oldItem.hashCode() == newItem.hashCode()
            }

        }
    )

    fun submitData(data: List<Product>) {
        asyncDataDiffer.submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //membuat instance of view holder
        return if (listMode == MODE_GRID) FoodGridItemViewHolder(
            ItemMenuGridBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        ) else {
            FoodListItemViewHolder(
                ItemMenuListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), listener
            )
        }
    }

    override fun getItemCount(): Int = asyncDataDiffer.currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder !is ViewHolderBinder<*>) return
        (holder as ViewHolderBinder<Product>).bind(asyncDataDiffer.currentList[position])
    }
}

interface OnItemClickedListener<T> {
    fun onItemClicked(item: T)
}
