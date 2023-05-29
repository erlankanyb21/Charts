package com.example.charts.ui.notifications

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.charts.R
import com.example.charts.databinding.ItemContactsRvBinding
import com.example.charts.ui.home.model.AddContactResponse

class PreviewContactsAdapter(
    var onItemClick: ((AddContactResponse) -> Unit)?
) : ListAdapter<AddContactResponse, PreviewContactsAdapter.ContactsViewHolder>(Companion) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val binding =
            ItemContactsRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class ContactsViewHolder(val binding: ItemContactsRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(businessContactUI: AddContactResponse) {
            binding.etContactNumber.text = businessContactUI.value

            when( businessContactUI.type){
                "telegram" ->
                    binding.ivContactType.setImageResource(R.drawable.telegramlogo)

                "phone" ->
                    binding.ivContactType.setImageResource(R.drawable.phonelogo)

                "whatsapp" ->
                    binding.ivContactType.setImageResource(R.drawable.whatsapplogo)

                "instagram" ->
                    binding.ivContactType.setImageResource(R.drawable.instagramlogo)

                "website" ->
                    binding.ivContactType.setImageResource(R.drawable.weblogo)

                "facebook" ->
                    binding.ivContactType.setImageResource(R.drawable.facebooklogo)

                "email" ->
                    binding.ivContactType.setImageResource(R.drawable.emaillogo)
            }

            itemView.setOnClickListener {
                onItemClick?.invoke(businessContactUI)
            }
        }
    }

    companion object : DiffUtil.ItemCallback<AddContactResponse>() {
        override fun areItemsTheSame(oldItem: AddContactResponse, newItem: AddContactResponse) =
            oldItem.type == newItem.type

        override fun areContentsTheSame(oldItem: AddContactResponse, newItem: AddContactResponse) =
            oldItem == newItem
    }
}