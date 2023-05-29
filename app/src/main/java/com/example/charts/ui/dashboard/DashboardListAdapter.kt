package com.example.charts.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.charts.R
import com.example.charts.databinding.ItemEditContactBinding

class DashboardListAdapter : RecyclerView.Adapter<DashboardListAdapter.ViewHolder>() {

    private val itemList: MutableList<DashboardItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemEditContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(icon: Int, type: String?) {
        if (itemList.size < 3) {
            val newItem = DashboardItem(icon = icon, type = type)
            itemList.add(newItem)
            notifyItemInserted(itemList.size - 1)
        }
    }

    fun removeItem(position: Int) {
        if (position in 0 until itemList.size) {
            itemList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun getItemData(position: Int): Pair<String, String?> {
        if (position in 0 .. itemList.size) {
            val item = itemList[position]
            val text = item.text.orEmpty()
            val type = item.type
            return Pair(text, type)
        }
        return Pair("", "")
    }

    inner class ViewHolder(private val binding: ItemEditContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DashboardItem) {
            binding.editLayout.setStartIconDrawable(item.icon)
            binding.editLayout.setEndIconDrawable(R.drawable.ic_stop)

            binding.editLayout.setEndIconOnClickListener {
                removeItem(adapterPosition)
            }
            binding.editText.addTextChangedListener {
                item.text = it.toString()
            }
        }
    }
}

data class DashboardItem(
    var text: String? = null,
    val icon: Int,
    val type: String? = null
)