package com.wony.remind.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wony.remind.databinding.ItemRemindBinding
import com.wony.remind.db.RemindData

class ListItemAdapter(private val fragment: ListFragment) :
    RecyclerView.Adapter<ListItemAdapter.ItemViewHolder>() {

    var items = listOf<RemindData.Item>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(private var binding: ItemRemindBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {

            var item = items[position]

            binding.run {
                tvTitle.text = item.title
                cbEnable.isChecked = item.isActive

                vm = fragment.viewModel
                id = item.id

                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRemindBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}